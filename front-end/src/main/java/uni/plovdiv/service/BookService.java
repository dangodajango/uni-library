package uni.plovdiv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.plovdiv.RestClient;
import uni.plovdiv.dto.author.AuthorInformationDto;
import uni.plovdiv.dto.book.BookCreateDto;
import uni.plovdiv.dto.book.BookInformationDto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
public class BookService {

    private final RestClient restClient;

    private final ObjectMapper objectMapper;

    public List<BookInformationDto> getAllBooks() {
        try {
            ResponseEntity<String> response =
                    restClient.sendRequest("http://localhost:8081/book/all", GET, new HttpHeaders(), String.class);
            return objectMapper.readValue(response.getBody(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, BookInformationDto.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void createBook(BookCreateDto bookCreateDto, String[] authors) {
        try {
            String requestBody = objectMapper.writeValueAsString(constructRequestBodyDto(bookCreateDto, authors));
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(APPLICATION_JSON);
            restClient.sendRequest("http://localhost:8081/book/create", POST, httpHeaders, requestBody, String.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private BookInformationDto constructRequestBodyDto(BookCreateDto bookCreateDto, String[] authors) {
        List<AuthorInformationDto> authorInformation = parseAuthors(authors);
        return BookInformationDto.builder()
                .title(bookCreateDto.getTitle())
                .isbn(bookCreateDto.getIsbn())
                .releaseDate(LocalDate.parse(bookCreateDto.getReleaseDate()))
                .price(bookCreateDto.getPrice())
                .authors(authorInformation)
                .build();
    }

    private List<AuthorInformationDto> parseAuthors(String[] authors) {
        return Arrays.stream(authors)
                .map(author -> {
                    String[] authorDetails = author.split(",");
                    String firstName = authorDetails[0];
                    String lastName = authorDetails[1];
                    String birthYear = authorDetails[2];
                    return AuthorInformationDto.builder()
                            .firstName(firstName)
                            .lastName(lastName)
                            .birthYear(LocalDate.parse(birthYear))
                            .build();
                }).toList();
    }

    public BookInformationDto findBookByIsbn(String isbn) {
        return getAllBooks()
                .stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    public void updateBook(String isbn, BookCreateDto bookCreateDto, String[] authors) {
        try {
            String requestBody = objectMapper.writeValueAsString(constructRequestBodyDto(bookCreateDto, authors));
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(APPLICATION_JSON);
            restClient.sendRequest(String.format("http://localhost:8081/book/update?isbn=%s", isbn), PUT, httpHeaders, requestBody, String.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBook(String isbn) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(APPLICATION_JSON);
        restClient.sendRequest(String.format("http://localhost:8081/book/delete?isbn=%s", isbn), DELETE, httpHeaders, String.class);
    }
}
