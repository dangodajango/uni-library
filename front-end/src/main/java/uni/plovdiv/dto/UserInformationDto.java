package uni.plovdiv.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class UserInformationDto {

    private String displayName;

    private String ucn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
