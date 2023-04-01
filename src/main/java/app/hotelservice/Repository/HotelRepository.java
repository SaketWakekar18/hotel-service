package app.hotelservice.Repository;

import app.hotelservice.Entity.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotels,String> {
    boolean existsByName(String name);
    boolean existsByLocation(String location);

}
