package com.one.eatmotion.controller;

import com.one.eatmotion.entity.review.Review;
import com.one.eatmotion.entity.review.TextReview;
import com.one.eatmotion.service.ReviewService;
import com.one.eatmotion.service.SentimentService;
import com.one.eatmotion.service.TextReviewService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

  /** ! Todo: 런타임 404 해결해야함 save는 잘 됨 * */
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "AUTH-TOKEN",
        value = "허가된 유요한 토큰",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @PostMapping("/{shopId}/review")
  public TextReview saveReview(@RequestBody String content, @PathVariable Long shopId) {
    return textReviewService.saveTextReview(content, shopId);
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
