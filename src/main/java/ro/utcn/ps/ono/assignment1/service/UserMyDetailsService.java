package ro.utcn.ps.ono.assignment1.service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.ps.ono.assignment1.entity.User;
import ro.utcn.ps.ono.assignment1.exception.UserNotFoundException;
import ro.utcn.ps.ono.assignment1.persistance.api.UserRepository;
import java.util.Collections;


@Service
@RequiredArgsConstructor
public class UserMyDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Unknown User!"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        // noi avem aici un singur rol, dar voi in aplicatie daca faceti si partea cu moderatori,
        // puteti aici sa adaugati rolurile si apoi daca mergeti in SecurityConfiguration sa vedeti ce am lasat comentariu
    }

    @Transactional
    public User loadCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
    }
}
