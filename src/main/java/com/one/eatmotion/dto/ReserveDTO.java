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

  LocalDateTime reserveDateTime;

  Integer reserveNumberOfPeople;
}