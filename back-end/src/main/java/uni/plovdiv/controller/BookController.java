package uni.plovdiv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.dto.book.BookInformationDTO;
import uni.plovdiv.service.BookService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/book")
@Tag(name = "Book endpoints")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve all books")
    List<BookInformationDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping(value = "/create")
    @Operation(summary = "Create a new book")
    public void createBook(
            @Valid @RequestBody BookInformationDTO bookInformationDTO
    ) {
        bookService.createBook(bookInformationDTO);
    }
}
