package uni.plovdiv.dto.book;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class BookInformationDTO {

    private String title;

    private String isbn;

    private LocalDate releaseDate;

    private Double price;
}
