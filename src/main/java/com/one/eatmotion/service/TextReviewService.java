package com.one.eatmotion.service;

import com.one.eatmotion.entity.review.TextReview;
import com.one.eatmotion.repository.TextReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TextReviewService {

  private final TextReviewRepository textReviewRepository;

  public TextReview saveTextReview(TextReview textReview) {
    return textReviewRepository.save(textReview);
  }
}
