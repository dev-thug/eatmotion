package com.one.eatmotion.service;

import com.one.eatmotion.entity.review.TextReview;
import com.one.eatmotion.repository.TextReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TextReviewService {

  private final TextReviewRepository textReviewRepository;
  private final SentimentService sentimentService;

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
