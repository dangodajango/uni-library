package uni.plovdiv.dto.author;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class FullAuthorInformationDto {

    private String firstName;

    private String lastName;

    private String nationality;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthYear;

    private boolean isAlive;
}
