package uni.plovdiv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.dto.book.BookInformationDTO;
import uni.plovdiv.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookInformationDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> BookInformationDTO.builder()
                        .title(book.getTitle())
                        .isbn(book.getIsbn())
                        .releaseDate(book.getReleaseDate())
                        .price(book.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
