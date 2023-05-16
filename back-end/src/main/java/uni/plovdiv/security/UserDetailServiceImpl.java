package uni.plovdiv.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uni.plovdiv.domain.model.Patron;
import uni.plovdiv.repository.PatronRepository;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final PatronRepository patronRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patron patron = patronRepository.findPatronByUsername(username).orElseThrow(
                () -> new IllegalStateException(String.format("Patron with such username - %s does not exist!", username)));
        return new UserDetailsImpl(patron);
    }
}
