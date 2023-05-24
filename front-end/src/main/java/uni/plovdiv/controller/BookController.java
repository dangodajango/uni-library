package uni.plovdiv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uni.plovdiv.dto.author.AuthorInformationDto;
import uni.plovdiv.dto.book.BookCreateDto;
import uni.plovdiv.dto.book.BookInformationDto;
import uni.plovdiv.service.AuthorService;
import uni.plovdiv.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/books")
@SessionAttributes("oldBookIsbn")
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
        List<AuthorInformationDto> authors = authorService.getAllAuthorsInformationForExternal();
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

    @RequestMapping("/edit/book-info")
    public String updateBook() {
        return "edit-book-info";
    }

    @PostMapping("/find")
    public String findBookByIsbn(
            @RequestParam String isbn,
            RedirectAttributes redirectAttributes
    ) {
        BookInformationDto book = bookService.findBookByIsbn(isbn);
        redirectAttributes.addFlashAttribute("book", book);
        return "redirect:/books/edit-page";
    }

    @RequestMapping("/edit-page")
    public String getBookEditPage(
            @ModelAttribute("book") BookInformationDto book,
            Model model
    ) {
        List<AuthorInformationDto> authors = authorService.getAllAuthorsInformationForExternal();
        model.addAttribute("oldBookIsbn", book.getIsbn());
        model.addAttribute("authors", authors);
        return "edit-book";
    }

    @PostMapping("/edit")
    public String editBook(
            @ModelAttribute("book") BookCreateDto bookCreateDto,
            @RequestParam("authors") String[] authorValues,
            Model model
    ) {
        String oldIsbn = (String) model.getAttribute("oldBookIsbn");
        bookService.updateBook(oldIsbn, bookCreateDto, authorValues);
        return "redirect:/books/";
    }

    @RequestMapping("/delete-page")
    public String getDeleteBookPage() {
        return "delete-book";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam String isbn) {
        bookService.deleteBook(isbn);
        return "redirect:/books/";
    }
}
