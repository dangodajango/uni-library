package uni.plovdiv.domain.dto.patron;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Builder
@Getter
public class PatronInformationDto {

    @Schema(description = "The display name of the patron", example = "John Doe")
    @NotBlank
    @Length(max = 40, message = "Display name can be max 25 symbols!")
    private String displayName;

    @Schema(description = "The unified civil number of the patron", example = "3031071080")
    @NotBlank
    @Length(min = 10, max = 10, message = "The UCN must be exactly 10 symbols!")
    private String ucn;

    @Schema(description = "The birth year of the author", example = "1995-07-09")
    @Past(message = "Birth year cannot be in the future, or today!")
    private LocalDate birthDate;
}
