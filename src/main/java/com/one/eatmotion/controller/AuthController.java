package com.one.eatmotion.controller;

import com.one.eatmotion.dto.UserDTO;
import com.one.eatmotion.entity.User;
import com.one.eatmotion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestParam String email, @RequestParam String password) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("token", userService.getToken(email, password));

        return ResponseEntity.accepted().body(map);
    }

    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDTO) {
        return userService.add(userDTO);
    }
}
