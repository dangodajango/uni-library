package uni.plovdiv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uni.plovdiv.dto.author.AuthorInformationDto;
import uni.plovdiv.dto.book.BookCreateDto;
import uni.plovdiv.service.AuthorService;
import uni.plovdiv.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final AuthorService authorService;

    @RequestMapping("/")
    public String books(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @RequestMapping("/create-page")
    public String createBook(Model model) {
        List<AuthorInformationDto> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "create-book";
    }

    @PostMapping("/create")
    public String createBook(
            @ModelAttribute("book") BookCreateDto bookCreateDto,
            @RequestParam("authors") String[] authorValues
    ) {
        bookService.createBook(bookCreateDto, authorValues);
        return "redirect:/books/";
    }

    @RequestMapping("/update-page")
    public String updateBook() {
        return "edit-book";
    }

    @RequestMapping("/delete-page")
    public String deleteBook() {
        return "delete-book";
    }
}
