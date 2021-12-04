package com.one.eatmotion.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Shop {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String name;

    Long x;

    Long y;

    // TODO: 2021-12-04 음식점 API에서 필요한 정보 추출후 담을 변수 선언
}
