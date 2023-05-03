package uni.plovdiv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.dto.author.AuthorInformationDTO;
import uni.plovdiv.model.Author;
import uni.plovdiv.repository.AuthorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<AuthorInformationDTO> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(author -> AuthorInformationDTO.builder()
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .nationality(author.getNationality())
                        .birthYear(author.getBirthYear())
                        .isAlive(author.isAlive())
                        .build())
                .collect(Collectors.toList());
    }

    public void createAuthor(String firstName, String lastName, String nationality, LocalDate birthYear, boolean isAlive) {
        Author author = Author.builder()
                .firstName(firstName)
                .lastName(lastName)
                .nationality(nationality)
                .birthYear(birthYear)
                .isAlive(isAlive)
                .build();
        authorRepository.save(author);
    }
}
