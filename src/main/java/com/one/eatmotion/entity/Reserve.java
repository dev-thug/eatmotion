package com.one.eatmotion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ToString
public class Reserve extends CommonDate {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private LocalDateTime reserveDateTime;

  private Integer reserveNumberOfPeople;

  @ManyToOne
  private User user;

  @ManyToOne
  private Shop shop;

}
