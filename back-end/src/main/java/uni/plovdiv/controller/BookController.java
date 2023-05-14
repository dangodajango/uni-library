package uni.plovdiv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.domain.dto.book.BookInformationDto;
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
    public List<BookInformationDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping(value = "/create")
    @Operation(summary = "Create a new book")
    public void createBook(
            @Valid @RequestBody BookInformationDto bookInformationDto
    ) {
        bookService.createBook(bookInformationDto);
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Update a book")
    public void updateBook(
            @Parameter(example = "9780306406157", required = true) @RequestParam String isbn,
            @Valid @RequestBody BookInformationDto updateBookInformation
    ) {
        bookService.updateBook(isbn, updateBookInformation);
    }

    @DeleteMapping(value = "/delete")
    @Operation(summary = "Delete a book")
    public void deleteBook(
            @Parameter(example = "9780306406157", required = true) @RequestParam String isbn
    ) {
        bookService.deleteBook(isbn);
    }

    @PutMapping(value = "/borrow")
    @Operation(summary = "Borrow a book")
    public void borrowBook(
            @Parameter(example = "9780306406157", required = true) @RequestParam String isbn,
            @Parameter(example = "3031071080", required = true) @RequestParam String patronUcn
    ) {
        bookService.borrowBook(isbn, patronUcn);
    }

    @PutMapping(value = "/return")
    @Operation(summary = "Return the borrowed book")
    public void returnBorrowedBook(
            @Parameter(example = "9780306406157", required = true) @RequestParam String isbn,
            @Parameter(example = "3031071080", required = true) @RequestParam String patronUcn
    ) {
        bookService.returnBorrowedBook(isbn, patronUcn);
    }
}
