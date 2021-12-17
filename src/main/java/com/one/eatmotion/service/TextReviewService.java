package com.one.eatmotion.service;

import com.one.eatmotion.entity.User;
import com.one.eatmotion.entity.review.TextReview;
import com.one.eatmotion.repository.TextReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TextReviewService {

  private final TextReviewRepository textReviewRepository;
  private final SentimentService sentimentService;
  private final UserService userService;
  private final ShopService shopService;

  /** onetoone으로 Receipt랑 Face 관계 맺고 가져오는 방법 */
  public List<TextReview> findAllByShopId(Long shopId) {
    return textReviewRepository.findAllByShopId(shopId);
  }

  @Transactional
  public TextReview saveTextReview(String content, Long shopId) {
    User user = userService.getAuthedUser();
    TextReview textReview = new TextReview();
    textReview.setContent(content);
    textReview.setUser(user);
    textReview.setShop(shopService.findById(shopId));
    textReview.setGrade(sentimentService.sentiment(textReview.getContent()));
    return textReviewRepository.save(textReview);
  }

  @Transactional
  public TextReview updateTextReview(Long id, String content) throws Exception {
    TextReview textReview = textReviewRepository.getById(id);
    User user = userService.getAuthedUser();
    if (!Objects.equals(user.getId(), textReview.getUser().getId())) {
      throw new Exception();
    }
    textReview.setContent(content);
    textReview.setGrade(sentimentService.sentiment(content));
    return textReview;
  }

  @Transactional
  public void deleteById(Long id) throws Exception {
    TextReview textReview = textReviewRepository.getById(id);
    User user = userService.getAuthedUser();
    if (!Objects.equals(user.getId(), textReview.getUser().getId())) {
      throw new Exception();
    }
    textReviewRepository.deleteById(id);
  }
}
