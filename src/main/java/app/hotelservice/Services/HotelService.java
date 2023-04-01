package app.hotelservice.Services;

import app.hotelservice.Entity.Hotels;
import app.hotelservice.Payloads.HotelResponse;

import java.util.List;

public interface HotelService {

    Hotels createHotel(Hotels hotels);

    Hotels updateHotel(Hotels hotels, String hotelId);

    List<Hotels> getAllHotels();

    Hotels getHotelByID(String hotelId);

    void deleteHotel(String hotelId);

    HotelResponse getAllHotelsWithPaginationAndSorting(int pageNumber, int pageSize, String sortBy);
}
