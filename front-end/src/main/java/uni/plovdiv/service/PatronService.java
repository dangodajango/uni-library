package uni.plovdiv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.plovdiv.RestClient;
import uni.plovdiv.dto.user.UserInformationDto;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
public class PatronService {

    private final RestClient restClient;

    private final ObjectMapper objectMapper;

    public List<UserInformationDto> getAllPatronsInformation() {
        try {
            ResponseEntity<String> response =
                    restClient.sendRequest("http://localhost:8081/patron/all", GET, new HttpHeaders(), String.class);
            return objectMapper.readValue(response.getBody(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, UserInformationDto.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserInformationDto> getAllPatronsInformation(String displayName, String ucn, LocalDate birthDate) {
        return getAllPatronsInformation()
                .stream()
                .filter(patron -> displayName == null || displayName.isBlank() || patron.getDisplayName().toLowerCase().contains(displayName.toLowerCase()))
                .filter(patron -> ucn == null || ucn.isBlank() || patron.getUcn().equals(ucn))
                .filter(patron -> birthDate == null || !(patron.getBirthDate() == null) && patron.getBirthDate().equals(birthDate))
                .toList();
    }

    public void deletePatron(String ucn) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(APPLICATION_JSON);
        restClient.sendRequest(String.format("http://localhost:8081/patron/delete?ucn=%s", ucn), DELETE, httpHeaders, String.class);
    }
}
