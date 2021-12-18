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
public class Reserve extends CommonDate {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private LocalDateTime reserveDateTime;

  private Integer reserveNumberOfPeople;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  private Shop shop;

  @Override
  public LocalDateTime getModifiedDate() {
    return super.getModifiedDate();
  }

  @Override
  public LocalDateTime getCreatedDate() {
    return super.getCreatedDate();
  }
}
