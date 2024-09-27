package org.katrin.feedbackme.configuration;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.katrin.feedbackme.entity.UserEntity;
import org.katrin.feedbackme.repository.User.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class UsersDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity employee = userRepository.findByEmail(username);

        Set<GrantedAuthority> authorities = employee.getRoles().stream()
                .map((roles) -> new SimpleGrantedAuthority(roles.toString()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                username,
                employee.getPassword(),
                authorities
        );
    }
}
