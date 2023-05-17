package uni.plovdiv.dto.book;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import uni.plovdiv.dto.author.AuthorInformationDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class BookInformationDto {

    private String title;

    private String isbn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private Double price;

    private List<AuthorInformationDto> authors;
}