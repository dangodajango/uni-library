package uni.plovdiv.dto.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class AuthorInformationDTO {

    @NotBlank
    @Length(max = 25, message = "First name can be max 25 symbols!")
    private String firstName;

    @NotBlank
    @Length(max = 25, message = "Last name can be max 25 symbols!")
    private String lastName;

    @NotBlank
    @Length(max = 25, message = "Nationality can be max 25 symbols!")
    private String nationality;

    @Past(message = "Birth year cannot be in the future, or today!")
    private LocalDate birthYear;

    private boolean isAlive;
}
