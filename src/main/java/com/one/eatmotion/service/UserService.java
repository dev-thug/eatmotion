package com.one.eatmotion.service;

import com.one.eatmotion.advice.exception.InvalidPasswordException;
import com.one.eatmotion.advice.exception.NotFoundEmailException;
import com.one.eatmotion.advice.exception.NotFoundUserException;
import com.one.eatmotion.config.security.TokenProvider;
import com.one.eatmotion.dto.UserDTO;
import com.one.eatmotion.entity.User;
import com.one.eatmotion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User getAuthedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(NotFoundEmailException::new);
    }

    public User findUser() {
        return this.getAuthedUser();
    }

    public String getToken(String email, String password) {
        User user = this.findByEmail(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            // 로그인 정보가 맞지 않음
            throw new InvalidPasswordException();
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

    public Page<User> findByUserRole(SimpleGrantedAuthority role, Pageable pageable) {
        return userRepository.findAllByRolesContaining(role, pageable);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }



}
