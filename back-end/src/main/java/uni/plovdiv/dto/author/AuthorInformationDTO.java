package uni.plovdiv.dto.author;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class AuthorInformationDTO {

    private String firstName;

    private String lastName;

    private String nationality;

    private LocalDate birthYear;

    private boolean isAlive;
}
