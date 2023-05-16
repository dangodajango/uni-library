package uni.plovdiv.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncrypter {

    private final PasswordEncoder passwordEncoder;

    public String encryptPassword(String nonEncryptedPassword) {
        return passwordEncoder.encode(nonEncryptedPassword);
    }
}
