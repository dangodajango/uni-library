package uni.plovdiv.dto.book;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class BookCreateDto {

    private String title;

    private String isbn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String releaseDate;

    private double price;
}
