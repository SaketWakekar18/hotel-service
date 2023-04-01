package app.hotelservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotels {
    @Id
    private String hotelId;
    @NotEmpty(message = "Hotel Name cannot be empty")
    private String name;
    @NotEmpty(message = "Hotel location cannot be empty")
    private String location;
    @NotEmpty(message = "About cannot be empty")
    private String about;
}
