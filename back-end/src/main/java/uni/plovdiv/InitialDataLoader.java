package uni.plovdiv;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import uni.plovdiv.domain.dto.patron.CreatePatronDto;
import uni.plovdiv.service.PatronService;

@Component
@RequiredArgsConstructor
public class InitialDataLoader implements ApplicationRunner {

    private final PatronService patronService;

    @Override
    public void run(ApplicationArguments args) {
        patronService.createPatron(CreatePatronDto.builder()
                .displayName("Admin")
                .ucn("0000000000")
                .username("admin")
                .password("admin")
                .build());
    }
}
