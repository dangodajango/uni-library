package uni.plovdiv.dto.patron;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Builder
@Getter
public class CreatePatronDto {

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

    @Schema(description = "The username of the patron", example = "user12345")
    @NotBlank
    @Length(min = 3, max = 20, message = "Username can be between 3 and 20 symbols!")
    private String username;

    @Schema(description = "The password of the patron", example = "41NvYPQBqDhHJgt5g4Fq")
    @NotBlank
    @Length(min = 7, max = 30, message = "Password must be between 7 and 30 symbols!")
    private String password;
}
