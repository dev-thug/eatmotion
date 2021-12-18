package com.one.eatmotion.entity.review;

import com.one.eatmotion.entity.CommonDate;
import com.one.eatmotion.entity.Shop;
import com.one.eatmotion.entity.User;
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
public class TextReview extends CommonDate {

  @Id @GeneratedValue private Long id;

  private String content;

  private Double grade;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  private Shop shop;

  @Override
  public LocalDateTime getCreatedDate() {
    return super.getCreatedDate();
  }

  @Override
  public LocalDateTime getModifiedDate() {
    return super.getModifiedDate();
  }
}
