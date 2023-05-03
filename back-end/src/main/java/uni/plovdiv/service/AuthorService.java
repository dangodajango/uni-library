package uni.plovdiv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.dto.author.AuthorInformationDTO;
import uni.plovdiv.repository.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorInformationDTO getAuthorInformation() {


        return AuthorInformationDTO.builder()
                .firstName()
                .lastName()
                .nationality()
                .birthYear()
                .isAlive()
                .build();
    }
}
