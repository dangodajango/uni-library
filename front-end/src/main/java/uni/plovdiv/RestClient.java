package uni.plovdiv;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public <T> ResponseEntity<T> sendRequest(String url, HttpMethod httpMethod, HttpHeaders httpHeaders, Class<T> responseType) {
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(url, httpMethod, request, responseType);
    }

    public <T> ResponseEntity<T> sendRequest(String url, HttpMethod httpMethod, HttpHeaders httpHeaders, String requestBody, Class<T> responseType) {
        HttpEntity<String> request = new HttpEntity<>(requestBody, httpHeaders);
        return restTemplate.exchange(url, httpMethod, request, responseType);
    }
}
