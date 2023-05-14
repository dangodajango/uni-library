package uni.plovdiv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.dto.author.AuthorBookDto;
import uni.plovdiv.dto.book.BookInformationDto;
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

    public List<BookInformationDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> BookInformationDto.builder()
                        .title(book.getTitle())
                        .isbn(book.getIsbn())
                        .releaseDate(book.getReleaseDate())
                        .price(book.getPrice())
                        .authors(book.getAuthors()
                                .stream()
                                .map(author -> AuthorBookDto.builder()
                                        .firstName(author.getFirstName())
                                        .lastName(author.getLastName())
                                        .birthYear(author.getBirthYear())
                                        .build())
                                .toList())
                        .build())
                .collect(Collectors.toList());
    }

    public void createBook(BookInformationDto bookInformationDto) {
        Book book = Book.builder()
                .title(bookInformationDto.getTitle())
                .isbn(bookInformationDto.getIsbn())
                .releaseDate(bookInformationDto.getReleaseDate())
                .price(bookInformationDto.getPrice())
                .authors(authorService.extractAuthorsFromDto(bookInformationDto))
                .borrowedBy(null)
                .build();
        bookRepository.save(book);
    }

    public void updateBook(String isbn, BookInformationDto updatedBookInformation) {
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
