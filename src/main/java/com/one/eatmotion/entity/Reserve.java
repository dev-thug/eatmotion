package com.one.eatmotion.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Reserve {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id; // ReserveId

  @CreatedDate private LocalDateTime userReserved = LocalDateTime.now();

  private LocalDateTime reserveDateTime;

  private Integer reserveNumberOfPeople;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  private Shop shop;
}
