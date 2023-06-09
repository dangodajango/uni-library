package uni.plovdiv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.domain.dto.patron.CreatePatronDto;
import uni.plovdiv.domain.dto.patron.PatronInformationDto;
import uni.plovdiv.domain.model.Patron;
import uni.plovdiv.repository.PatronRepository;
import uni.plovdiv.security.PasswordEncrypter;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatronService {

    private final PatronRepository patronRepository;

    private final PasswordEncrypter passwordEncrypter;

    public void createPatron(CreatePatronDto createPatronDto) {
        patronRepository.save(Patron.builder()
                .displayName(createPatronDto.getDisplayName())
                .ucn(createPatronDto.getUcn())
                .birthDate(createPatronDto.getBirthDate())
                .username(createPatronDto.getUsername())
                .password(passwordEncrypter.encryptPassword(createPatronDto.getPassword()))
                .build());
    }

    public List<PatronInformationDto> getAllPatrons() {
        return patronRepository.findAll()
                .stream()
                .map(patron -> PatronInformationDto.builder()
                        .displayName(patron.getDisplayName())
                        .ucn(patron.getUcn())
                        .birthDate(patron.getBirthDate())
                        .build())
                .toList();
    }

    public PatronInformationDto getPatronFromUsername(String username) {
        Patron patron = patronRepository.findPatronByUsername(username)
                .orElseThrow(() -> new IllegalStateException(String.format("Patron with such username - %s does not exist!", username)));
        return PatronInformationDto.builder()
                .displayName(patron.getDisplayName())
                .ucn(patron.getUcn())
                .birthDate(patron.getBirthDate())
                .build();
    }

    public void updatePatron(String ucn, PatronInformationDto updatedPatronInformation) {
        Patron patronToBeUpdated = patronRepository.findPatronByUcn(ucn)
                .orElseThrow(() -> new IllegalStateException(String.format("Patron with such ucn - %s does not exist!", ucn)));
        patronRepository.save(Patron.builder()
                .id(patronToBeUpdated.getId())
                .username(patronToBeUpdated.getUsername())
                .password(patronToBeUpdated.getPassword())
                .displayName(updatedPatronInformation.getDisplayName())
                .ucn(updatedPatronInformation.getUcn())
                .birthDate(updatedPatronInformation.getBirthDate())
                .build());
    }

    public void deletePatron(String ucn) {
        Patron patronToBeDeleted = patronRepository.findPatronByUcn(ucn)
                .orElseThrow(() -> new IllegalStateException(String.format("Patron with such ucn - %s does not exist!", ucn)));
        patronRepository.delete(patronToBeDeleted);
    }
}
