package com.one.eatmotion.repository;

import com.one.eatmotion.entity.review.TextReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextReviewRepository extends JpaRepository<TextReview, Long> {}
