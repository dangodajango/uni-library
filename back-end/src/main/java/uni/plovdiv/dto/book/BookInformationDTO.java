package uni.plovdiv.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Length;
import uni.plovdiv.dto.author.AuthorBookDTO;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class BookInformationDTO {

    @Schema(description = "The title of the book", example = "Under the Yoke")
    @NotBlank
    @Length(max = 25, message = "Title can be max 25 symbols!")
    private String title;

    @Schema(description = "The unique identifier of the book", example = "9780306406157")
    @NotBlank
    @ISBN(message = "Should be valid 13-digit ISBN!")
    private String isbn;

    @Schema(description = "The release date of the book", example = "2022-01-01")
    private LocalDate releaseDate;

    @Schema(description = "The price of the book", example = "23.35")
    @DecimalMin(value = "0.0", message = "Price cannot be less than 0.0!")
    private Double price;

    private List<AuthorBookDTO> authors;
}
