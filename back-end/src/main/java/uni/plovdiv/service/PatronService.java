package uni.plovdiv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.domain.dto.patron.CreatePatronDto;
import uni.plovdiv.domain.dto.patron.PatronInformationDto;
import uni.plovdiv.domain.model.Patron;
import uni.plovdiv.repository.PatronRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatronService {

    private final PatronRepository patronRepository;

    public void createPatron(CreatePatronDto createPatronDto) {
        patronRepository.save(Patron.builder()
                .displayName(createPatronDto.getDisplayName())
                .ucn(createPatronDto.getUcn())
                .birthDate(createPatronDto.getBirthDate())
                .username(createPatronDto.getUsername())
                .password(createPatronDto.getPassword())
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
}
