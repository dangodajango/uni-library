package uni.plovdiv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.plovdiv.RestClient;
import uni.plovdiv.dto.authentication.SignInDto;
import uni.plovdiv.dto.authentication.SignUpDto;

import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ObjectMapper objectMapper;

    private final RestClient restClient;

    /**
     * @return username of the logged in person
     */
    public String signInUser(SignInDto signInDto) {
        String signInUrl = "http://localhost:8081/login";
        HttpHeaders httpHeaders = createBasicAuthHeader(signInDto.getUsername(), signInDto.getPassword());
        ResponseEntity<String> response = restClient.sendRequest(signInUrl, POST, httpHeaders, String.class);
        return response.getBody();
    }

    private HttpHeaders createBasicAuthHeader(String username, String password) {
        String basicAuthToken = createBasicAuthToken(username, password);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", basicAuthToken);
        return httpHeaders;
    }

    private String createBasicAuthToken(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        return "Basic " + new String(encodedAuth);
    }

    public void signUpUser(SignUpDto signUpDto) {
        try {
            String signUpUrl = "http://localhost:8081/patron/create";
            String requestBody = objectMapper.writeValueAsString(signUpDto);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(APPLICATION_JSON);
            restClient.sendRequest(signUpUrl, POST, httpHeaders, requestBody, String.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void logout() {
        restClient.sendRequest("http://localhost:8081/logout", POST, new HttpHeaders(), String.class);
    }
}
