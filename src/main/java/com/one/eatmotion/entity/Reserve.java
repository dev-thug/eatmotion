package com.one.eatmotion.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Reserve {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  Long id; // ReserveId

  String reserveDate;

  Integer reserveNumberOfPeople;

  @ManyToOne Shop shop;
  @ManyToOne User user;
}
