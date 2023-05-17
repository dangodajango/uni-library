package uni.plovdiv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class SignUpDto {

    private String displayName;

    private String ucn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @JsonProperty("username")
    private String newUsername;

    @JsonProperty("password")
    private String newPassword;

}
