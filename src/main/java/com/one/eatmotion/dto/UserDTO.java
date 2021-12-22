package com.one.eatmotion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  @Email(message = "올바른 형식의 이메일 주소를 작성해주세요.")
  private String email;

  @Min(value = 10, message = "비밀번호의 최소 길이는 10자리 입니다.")
  private String password;

  @NotBlank(message = "이름을 작성해주세요")
  private String name;
}
