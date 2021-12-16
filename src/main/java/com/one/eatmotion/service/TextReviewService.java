package com.one.eatmotion.service;

import com.one.eatmotion.entity.User;
import com.one.eatmotion.entity.review.TextReview;
import com.one.eatmotion.repository.TextReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TextReviewService {

  private final TextReviewRepository textReviewRepository;
  private final SentimentService sentimentService;
  private final UserService userService;
  private final ShopService shopService;

  /** onetoone으로 Receipt랑 Face 관계 맺고 가져오는 방법 */
  public List<TextReview> findAllByShopId(Long shopId) {
    return textReviewRepository.findAllByShopId(shopId);
  }

  public TextReview saveTextReview(String content, Long shopId) {
    User user = userService.getAuthedUser();
    TextReview textReview = new TextReview();
    textReview.setContent(content);
    textReview.setUser(user);
    textReview.setShop(shopService.findById(shopId));
    textReview.setGrade(sentimentService.sentiment(textReview.getContent()));
    System.out.println(textReview);
    return textReviewRepository.save(textReview);
  }

  @Transactional
  public TextReview updateTextReview(Long id, String content) {
    TextReview textReview = textReviewRepository.getById(id);
    textReview.setContent(content);
    textReview.setGrade(sentimentService.sentiment(content));
    return textReview;
  }

  @Transactional
  public void deleteById(Long id) {
    textReviewRepository.deleteById(id);
  }
}
