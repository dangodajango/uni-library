package uni.plovdiv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.plovdiv.RestClient;
import uni.plovdiv.dto.author.AuthorInformationDto;
import uni.plovdiv.dto.author.FullAuthorInformationDto;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final RestClient restClient;

    private final ObjectMapper objectMapper;

    public List<AuthorInformationDto> getAllAuthors() {
        try {
            ResponseEntity<String> response =
                    restClient.sendRequest("http://localhost:8081/author/all", GET, new HttpHeaders(), String.class);
            List<FullAuthorInformationDto> authors = objectMapper.readValue(response.getBody(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, FullAuthorInformationDto.class));
            return authors.stream()
                    .map(author -> AuthorInformationDto.builder()
                            .firstName(author.getFirstName())
                            .lastName(author.getLastName())
                            .birthYear(author.getBirthYear())
                            .workAroundValue(author.getFirstName() + "," + author.getLastName() + "," + author.getBirthYear())
                            .build())
                    .toList();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
