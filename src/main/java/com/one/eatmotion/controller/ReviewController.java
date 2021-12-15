package com.one.eatmotion.controller;

import com.one.eatmotion.entity.review.Review;
import com.one.eatmotion.entity.review.TextReview;
import com.one.eatmotion.service.ReviewService;
import com.one.eatmotion.service.SentimentService;
import com.one.eatmotion.service.TextReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;
  private final TextReviewService textReviewService;
  private final SentimentService sentimentService;

  /** 런타임 404 * */
  @PostMapping("/review/{shopId}/save")
  public TextReview saveReview(@PathVariable Long shopId, TextReview textReview) {
    textReviewService.saveTextReview(textReview);
    textReview.setGrade(Double.valueOf(sentimentService.sentiment(textReview.getContent())));
    return textReview;
  }
}
