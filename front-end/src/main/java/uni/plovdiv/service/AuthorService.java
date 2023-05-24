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

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final RestClient restClient;

    private final ObjectMapper objectMapper;

    public List<AuthorInformationDto> getAllAuthorsInformationForExternal() {
        return getAllAuthorsFullInformation()
                .stream()
                .map(author -> AuthorInformationDto.builder()
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .birthYear(author.getBirthYear())
                        .workAroundValue(author.getFirstName() + "," + author.getLastName() + "," + author.getBirthYear())
                        .build())
                .toList();
    }

    public List<FullAuthorInformationDto> getAllAuthorsFullInformation() {
        try {
            ResponseEntity<String> response =
                    restClient.sendRequest("http://localhost:8081/author/all", GET, new HttpHeaders(), String.class);
            return objectMapper.readValue(response.getBody(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, FullAuthorInformationDto.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public FullAuthorInformationDto findAuthorByFirstNameLastNameBirthYear(String firstName, String lastName, LocalDate birthYear) {
        return getAllAuthorsFullInformation().stream()
                .filter(author ->
                        author.getFirstName().equals(firstName)
                                && author.getLastName().equals(lastName)
                                && author.getBirthYear().equals(birthYear)
                )
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public void createAuthor(FullAuthorInformationDto fullAuthorInformationDto) {
        try {
            String requestBody = objectMapper.writeValueAsString(fullAuthorInformationDto);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(APPLICATION_JSON);
            restClient.sendRequest("http://localhost:8081/author/create", POST, httpHeaders, requestBody, String.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void editAuthor(FullAuthorInformationDto oldAuthor, FullAuthorInformationDto updatedAuthor) {
        try {
            String uri = String.format("http://localhost:8081/author/update?firstName=%s&lastName=%s&birthYear=%s",
                    oldAuthor.getFirstName(), oldAuthor.getLastName(), oldAuthor.getBirthYear());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(APPLICATION_JSON);
            String requestBody = objectMapper.writeValueAsString(updatedAuthor);
            restClient.sendRequest(uri, PUT, httpHeaders, requestBody, String.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAuthor(String firstName, String lastName, LocalDate birthYear) {
        String uri = String.format("http://localhost:8081/author/delete?firstName=%s&lastName=%s&birthYear=%s", firstName, lastName, birthYear);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(APPLICATION_JSON);
        String emptyRequestBody = "";
        restClient.sendRequest(uri, DELETE, httpHeaders, emptyRequestBody, String.class);
    }
}
