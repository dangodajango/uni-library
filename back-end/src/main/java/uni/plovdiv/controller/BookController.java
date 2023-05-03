package uni.plovdiv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.dto.book.BookInformationDTO;
import uni.plovdiv.service.BookService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping(name = "/all", produces = APPLICATION_JSON_VALUE)
    List<BookInformationDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

}
