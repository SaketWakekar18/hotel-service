package app.hotelservice.Exceptions;

import lombok.Data;

@Data
public class HotelAlreadyExistsException extends RuntimeException {
    String message;

    public HotelAlreadyExistsException(String message) {
        this.message = message;
    }
}
