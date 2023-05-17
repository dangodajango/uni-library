package uni.plovdiv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.plovdiv.RestClient;
import uni.plovdiv.dto.user.UserInformationDto;

import javax.servlet.http.HttpSession;

import static org.springframework.http.HttpMethod.GET;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RestClient restClient;

    public UserInformationDto getUserInformation(HttpSession httpSession) {
        String username = (String) httpSession.getAttribute("username");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<UserInformationDto> response = restClient.sendRequest(
                String.format("http://localhost:8081/patron/%s", username), GET, headers, UserInformationDto.class);
        return response.getBody();
    }
}
