package com.one.eatmotion.entity.review;

import com.one.eatmotion.entity.Shop;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Shop shop;
}

/**
 * textReviewGrade * A + faceReviewGrade * B + receiptReview * C / A + B + C
 *
 * <p>선택하는 항목에서 기본적으로는 전체 평점을 평균내서 보여줌 선택하는 거에서 영수증 인증된 평점 보기 얼굴 평점 보기
 */
