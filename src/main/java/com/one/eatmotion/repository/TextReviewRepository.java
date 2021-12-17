package com.one.eatmotion.repository;

import com.one.eatmotion.entity.review.TextReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface TextReviewRepository extends JpaRepository<TextReview, Long> {
  List<TextReview> findAllByShopId(Long shopId);

  ArrayList<Double> findGradeByShopId(Long shopId);
}
