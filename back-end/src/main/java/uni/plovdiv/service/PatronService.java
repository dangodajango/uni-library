package uni.plovdiv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.dto.patron.PatronInformationDTO;
import uni.plovdiv.model.Patron;
import uni.plovdiv.repository.PatronRepository;

@Service
@RequiredArgsConstructor
public class PatronService {

    private final PatronRepository patronRepository;

    public void createPatron(PatronInformationDTO patronInformationDTO) {
        patronRepository.save(Patron.builder()
                .displayName(patronInformationDTO.getDisplayName())
                .ucn(patronInformationDTO.getUcn())
                .birthDate(patronInformationDTO.getBirthDate())
                .username(patronInformationDTO.getUsername())
                .password(patronInformationDTO.getPassword())
                .build());
    }
}
