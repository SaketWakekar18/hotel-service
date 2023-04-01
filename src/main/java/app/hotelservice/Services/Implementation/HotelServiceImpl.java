package app.hotelservice.Services.Implementation;

import app.hotelservice.Entity.Hotels;
import app.hotelservice.Exceptions.HotelAlreadyExistsException;
import app.hotelservice.Exceptions.ResourceNotFoundException;
import app.hotelservice.Payloads.HotelResponse;
import app.hotelservice.Repository.HotelRepository;
import app.hotelservice.Services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Hotels createHotel(Hotels hotels) {
        String randomID = UUID.randomUUID().toString();
        hotels.setHotelId(randomID);
        if (!this.hotelRepository.existsByName(hotels.getName()) || !this.hotelRepository.existsByLocation(hotels.getLocation())) {
            Hotels createHotel = this.hotelRepository.save(hotels);
            return createHotel;
        } else {
            throw new HotelAlreadyExistsException("Hotel already exists");
        }
    }

    @Override
    public Hotels updateHotel(Hotels hotels, String hotelId) {
        Hotels getHotels = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));
        getHotels.setName(hotels.getName());
        getHotels.setLocation(hotels.getLocation());
        getHotels.setAbout(hotels.getAbout());
        Hotels updateHotels = this.hotelRepository.save(getHotels);
        return updateHotels;
    }

    @Override
    public List<Hotels> getAllHotels() {
        List<Hotels> allHotels = this.hotelRepository.findAll();
        return allHotels;
    }

    @Override
    public Hotels getHotelByID(String hotelId) {
        return this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));
    }

    @Override
    public void deleteHotel(String hotelId) {
        Hotels hotels = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));
        this.hotelRepository.delete(hotels);
    }

    @Override
    public HotelResponse getAllHotelsWithPaginationAndSorting(int pageNumber, int pageSize, String sortBy) {
        Page<Hotels> hotelsPage = this.hotelRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending()));
        List<Hotels> hotelList = hotelsPage.stream().toList();
        HotelResponse hotelResponse = new HotelResponse();
        hotelResponse.setContent(hotelList);
        hotelResponse.setPageNumber(hotelsPage.getNumber());
        hotelResponse.setPageSize(hotelsPage.getSize());
        hotelResponse.setTotalPages(hotelsPage.getTotalPages());
        hotelResponse.setTotalElements(hotelsPage.getTotalElements());
        hotelResponse.setLastPage(hotelsPage.isLast());
        return hotelResponse;
    }
}
