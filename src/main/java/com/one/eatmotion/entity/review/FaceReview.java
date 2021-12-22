package com.one.eatmotion.entity.review;

import com.one.eatmotion.entity.CommonDate;
import com.one.eatmotion.entity.Shop;
import com.one.eatmotion.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ToString
public class FaceReview extends CommonDate {

  @Id @GeneratedValue private Long id;

  private String age;

  private String emotion;

  private String filePath;

  @ManyToOne
  private User user;

  @ManyToOne
  private Shop shop;
}
