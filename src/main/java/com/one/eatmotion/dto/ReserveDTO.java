package com.one.eatmotion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReserveDTO {

  /** Todo: 유효성 체크 필요 **/

  LocalDateTime reserveDateTime;

  Integer reserveNumberOfPeople;
}
