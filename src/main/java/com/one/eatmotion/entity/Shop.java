package com.one.eatmotion.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ToString
@Table(name = "Shop")
public class Shop {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  Long id;

  String name;

  Double x;

  Double y;

  String address;

  String jachi;

  String foodClassific;


  // TODO: 2021-12-04 음식점 API에서 필요한 정보 추출후 담을 변수 선언
}
