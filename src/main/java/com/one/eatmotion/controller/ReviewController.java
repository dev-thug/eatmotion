package com.one.eatmotion.controller;

import com.one.eatmotion.entity.review.Review;
import com.one.eatmotion.entity.review.TextReview;
import com.one.eatmotion.service.ReviewService;
import com.one.eatmotion.service.SentimentService;
import com.one.eatmotion.service.TextReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;
  private final TextReviewService textReviewService;
  private final SentimentService sentimentService;

  @GetMapping("/{shopId}/review")
  public List<TextReview> listReview(@PathVariable Long shopId) {
    return textReviewService.findAllByShopId(shopId);
  }

  /** 런타임 404 * */
  @PostMapping("/{shopId}/review")
  public TextReview saveReview(@PathVariable Long shopId, TextReview textReview) {
    return textReviewService.saveTextReview(textReview);
  }

  @PutMapping("/{shopId}/review")
  public TextReview updateReview(@PathVariable Long shopId, Long id, String content) {
    return textReviewService.updateTextReview(id, content);
  }

  @DeleteMapping("/{shopId}/review")
  public void deleteReview(@PathVariable Long shopId, Long id) {
    textReviewService.deleteById(id);
  }
}
