package uni.plovdiv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.dto.author.AuthorBookDTO;
import uni.plovdiv.dto.book.BookInformationDTO;
import uni.plovdiv.model.Book;
import uni.plovdiv.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    public List<BookInformationDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> BookInformationDTO.builder()
                        .title(book.getTitle())
                        .isbn(book.getIsbn())
                        .releaseDate(book.getReleaseDate())
                        .price(book.getPrice())
                        .authors(book.getAuthors()
                                .stream()
                                .map(author -> AuthorBookDTO.builder()
                                        .firstName(author.getFirstName())
                                        .lastName(author.getLastName())
                                        .birthYear(author.getBirthYear())
                                        .build())
                                .toList())
                        .build())
                .collect(Collectors.toList());
    }

    public void createBook(BookInformationDTO bookInformationDTO) {
        Book book = Book.builder()
                .title(bookInformationDTO.getTitle())
                .isbn(bookInformationDTO.getIsbn())
                .releaseDate(bookInformationDTO.getReleaseDate())
                .price(bookInformationDTO.getPrice())
                .authors(authorService.extractAuthorsFromDto(bookInformationDTO))
                .borrowedBy(null)
                .build();
        bookRepository.save(book);
    }

    public void updateBook(String isbn, BookInformationDTO updatedBookInformation) {
        Optional<Book> bookOptional = bookRepository.findBookByIsbn(isbn);
        bookOptional.ifPresentOrElse((book) -> bookRepository.save(Book.builder()
                .id(book.getId())
                .title(updatedBookInformation.getTitle())
                .isbn(updatedBookInformation.getIsbn())
                .releaseDate(updatedBookInformation.getReleaseDate())
                .price(updatedBookInformation.getPrice())
                .authors(authorService.extractAuthorsFromDto(updatedBookInformation))
                .build()), () -> {
            throw new IllegalStateException(String.format("Book with %s isbn does not exist in the database", isbn));
        });
    }

    public void deleteBook(String isbn) {
        Optional<Book> bookOptional = bookRepository.findBookByIsbn(isbn);
        bookOptional.ifPresent(bookRepository::delete);
    }
}
