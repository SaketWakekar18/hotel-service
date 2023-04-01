package app.hotelservice.Services;

import app.hotelservice.Entity.Hotels;
import app.hotelservice.Payloads.HotelResponse;

import java.util.List;

public interface HotelService {

    Hotels createHotel(Hotels hotels);

    Hotels updateHotel(Hotels hotels, int hotelId);

    List<Hotels> getAllHotels();

    Hotels getHotelByID(int hotelId);

    void deleteHotel(int hotelId);

    HotelResponse getAllHotelsWithPaginationAndSorting(int pageNumber, int pageSize, String sortBy);
    Hotels searchHotel(String name);
}
