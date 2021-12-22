package com.one.eatmotion.entity.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.one.eatmotion.entity.CommonDate;
import com.one.eatmotion.entity.Shop;
import com.one.eatmotion.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ToString
public class TextReview extends CommonDate {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String content;

    private Double grade;

    /** **/
  @ManyToOne
  private User user;

  @ManyToOne
  private Shop shop;
}
