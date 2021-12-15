package com.one.eatmotion.service;

import com.one.eatmotion.entity.review.TextReview;
import com.one.eatmotion.repository.TextReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TextReviewService {

  private final TextReviewRepository textReviewRepository;
  private final SentimentService sentimentService;

  /** onetoone으로 Receipt랑 Face 관계 맺고 가져오는 방법 */
  public List<TextReview> findAllByShopId(Long shopId) {
    return textReviewRepository.findAllByShopId(shopId);
  }

  @Transactional
  public TextReview saveTextReview(TextReview textReview) {
    textReviewRepository.save(textReview);
    textReview.setGrade(Double.valueOf(sentimentService.sentiment(textReview.getContent())));
    return textReview;
  }

  @Transactional
  public TextReview updateTextReview(Long id, String content) {
    TextReview textReview = textReviewRepository.getById(id);
    textReview.setContent(content);
    textReview.setGrade(Double.valueOf(sentimentService.sentiment(content)));
    return textReview;
  }

  @Transactional
  public void deleteById(Long id) {
    textReviewRepository.deleteById(id);
  }
}
