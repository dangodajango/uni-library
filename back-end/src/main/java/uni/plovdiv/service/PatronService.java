package uni.plovdiv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.plovdiv.dto.patron.CreatePatronDto;
import uni.plovdiv.model.Patron;
import uni.plovdiv.repository.PatronRepository;

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
}
