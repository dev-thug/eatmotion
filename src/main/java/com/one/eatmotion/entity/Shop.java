package com.one.eatmotion.entity;

import com.one.eatmotion.entity.review.TextReview;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
  private Long id;

  private String name;

  private Double x;

  private Double y;

  private String address;

  private String jachi;

  private String foodClassific;

  private Double grade;

  @ElementCollection private List<String> menu;

  // TODO: 2021-12-04 음식점 API에서 필요한 정보 추출후 담을 변수 선언
}
