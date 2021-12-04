package com.one.eatmotion.controller;

import com.one.eatmotion.dto.UserDTO;
import com.one.eatmotion.entity.User;
import com.one.eatmotion.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = {"1, 회원 인증"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;

    @ApiOperation(value = "회원 로그인", notes = "회원의 Email, Password로 로그인 후 JWT토큰 발급")
    @PostMapping("/login")
    public ResponseEntity<?> signIn(
            @ApiParam(value = "회원 이메일", required = true) @RequestParam String email,
            @ApiParam(value = "회원 비밀번호", required = true) @RequestParam String password) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("AUTH-TOKEN", userService.getToken(email, password));

        return ResponseEntity.accepted().body(map);
    }

    @ApiOperation(value = "회원 가입", notes = "Email, Password, Name 으로 회원 가입")
    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDTO) {
        return userService.add(userDTO);
    }
}
