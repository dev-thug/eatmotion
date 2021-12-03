package com.one.eatmotion.service;

import com.one.eatmotion.config.security.TokenProvider;
import com.one.eatmotion.dto.UserDTO;
import com.one.eatmotion.entity.User;
import com.one.eatmotion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService {

    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User getAuthedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow();
    }

    public User get(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public User get() {
        return this.getAuthedUser();
    }

    public String getToken(String email, String password) throws Exception {
        User user = this.get(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            // 로그인 정보가 맞지 않음
            throw new Exception();
        }

        return tokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());
    }

    public User add(User user) {
        return userRepository.save(user);
    }


        public User add(UserDTO userDTO) {
        User user = User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .name(userDTO.getName())
                .roles(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
                .build();

        return this.add(user);
    }

}
