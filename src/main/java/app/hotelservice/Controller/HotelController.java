package app.hotelservice.Controller;

import app.hotelservice.Entity.Hotels;
import app.hotelservice.Payloads.APIResponse;
import app.hotelservice.Payloads.AppConstants;
import app.hotelservice.Payloads.HotelResponse;
import app.hotelservice.Services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("create")
    public ResponseEntity<Hotels> createHotel(@Valid @RequestBody Hotels hotels) {
        Hotels createdHotel = this.hotelService.createHotel(hotels);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @PutMapping("/update/{hotelId}")
    public ResponseEntity<Hotels> updateHotel(@Valid @RequestBody Hotels hotels, @PathVariable int hotelId) {
        Hotels updatedHotel = this.hotelService.updateHotel(hotels, hotelId);
        return ResponseEntity.ok(updatedHotel);
    }

    //@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/hotelList")
    public ResponseEntity<List<Hotels>> getAllHotels() {
        List<Hotels> allHotels = this.hotelService.getAllHotels();
        return ResponseEntity.ok(allHotels);
    }

    @GetMapping("/getHotelsByID/{hotelId}")
    public ResponseEntity<Hotels> getHotelsByID(@PathVariable int hotelId) {
        Hotels hotelByID = this.hotelService.getHotelByID(hotelId);
        return ResponseEntity.ok(hotelByID);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/delete/{hotelId}")
    public ResponseEntity<APIResponse> deleteHotel(@PathVariable int hotelId) {
        this.hotelService.deleteHotel(hotelId);
        return new ResponseEntity<>(new APIResponse("Hotel deleted successfully!", true), HttpStatus.GONE);
    }

    @GetMapping("/pagination")
    public ResponseEntity<HotelResponse> getAllPostsByUserWithPaginationAndSorting(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
                                                                                   @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
                                                                                   @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy) {
        return ResponseEntity.ok(this.hotelService.getAllHotelsWithPaginationAndSorting(pageNumber, pageSize, sortBy));
    }

    @GetMapping("/searchHotel")
    public ResponseEntity<Hotels> searchHotels(@RequestParam String name) {
        Hotels hotels = this.hotelService.searchHotel(name);
        return ResponseEntity.ok(hotels);
    }
}
