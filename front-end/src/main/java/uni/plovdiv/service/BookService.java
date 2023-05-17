package uni.plovdiv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.plovdiv.RestClient;
import uni.plovdiv.dto.book.BookInformationDto;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

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
}
