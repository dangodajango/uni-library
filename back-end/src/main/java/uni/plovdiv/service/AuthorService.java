package uni.plovdiv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.dto.author.AuthorInformationDto;
import uni.plovdiv.dto.book.BookInformationDto;
import uni.plovdiv.model.Author;
import uni.plovdiv.repository.AuthorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<AuthorInformationDto> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(author -> AuthorInformationDto.builder()
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .nationality(author.getNationality())
                        .birthYear(author.getBirthYear())
                        .isAlive(author.isAlive())
                        .build())
                .collect(Collectors.toList());
    }

    public void createAuthor(AuthorInformationDto authorInformationDto) {
        Author author = Author.builder()
                .firstName(authorInformationDto.getFirstName())
                .lastName(authorInformationDto.getLastName())
                .nationality(authorInformationDto.getNationality())
                .birthYear(authorInformationDto.getBirthYear())
                .isAlive(authorInformationDto.isAlive())
                .build();
        authorRepository.save(author);
    }

    public void updateAuthor(String firstName, String lastName, LocalDate birthYear, AuthorInformationDto updatedAuthorInformation) {
        Optional<Author> authorOptional = authorRepository.findAuthorByFirstNameLastNameBirthDate(firstName, lastName, birthYear);
        authorOptional.ifPresentOrElse((author) -> authorRepository.save(Author.builder()
                .id(author.getId())
                .firstName(updatedAuthorInformation.getFirstName())
                .lastName(updatedAuthorInformation.getLastName())
                .nationality(updatedAuthorInformation.getNationality())
                .birthYear(updatedAuthorInformation.getBirthYear())
                .isAlive(updatedAuthorInformation.isAlive())
                .build()), () -> {
            throw new IllegalStateException(String.format("Author(%s, %s, %s) does not exist in the database.", firstName, lastName, birthYear));
        });
    }

    public void deleteAuthor(String firstName, String lastName, LocalDate birthYear) {
        authorRepository.deleteAuthorFromFirstNameLastNameBirthDate(firstName, lastName, birthYear);
    }

    public Set<Author> extractAuthorsFromDto(BookInformationDto bookInformationDto) {
        return bookInformationDto.getAuthors()
                .stream()
                .map(authorDTO -> authorRepository.findAuthorByFirstNameLastNameBirthDate(
                        authorDTO.getFirstName(),
                        authorDTO.getLastName(),
                        authorDTO.getBirthYear()))
                .map(authorOptional -> authorOptional.orElseThrow(() -> new IllegalStateException("Author does not exist in the database!")))
                .collect(Collectors.toSet());
    }
}
