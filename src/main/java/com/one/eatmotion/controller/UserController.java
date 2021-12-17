package com.one.eatmotion.controller;

import com.one.eatmotion.entity.User;
import com.one.eatmotion.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"2. 사용자"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class UserController {

  private final UserService userService;

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "authToken",
        value = "허가된 유요한 토큰",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @ApiOperation(value = "내 정보 조회", notes = "회원이 자신의 정보를 조회")
  @GetMapping(value = "me")
  public ResponseEntity<User> findMe() {
    return ResponseEntity.ok(userService.findUser());
  }

  @GetMapping(value = "users")
  public List<User> users() {
    return userService.findAll();
  }
}
