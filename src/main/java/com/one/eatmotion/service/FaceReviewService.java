package com.one.eatmotion.service;

import com.one.eatmotion.entity.User;
import com.one.eatmotion.entity.review.FaceReview;
import com.one.eatmotion.repository.FaceReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FaceReviewService {

  private final FaceReviewRepository faceReviewRepository;
  private final UserService userService;
  private final ShopService shopService;
  private final NaverFaceRecognition naverFaceRecognition;

  public FaceReview save(Long shopId, String uploadFileName) throws IOException {
    User user = userService.getAuthedUser();
    FaceReview faceReview = new FaceReview();

    String emotion = naverFaceRecognition.face(uploadFileName).get("emotion");
    String age = naverFaceRecognition.face(uploadFileName).get("age");

    faceReview.setEmotion(emotion);
    faceReview.setAge(age);
    faceReview.setFilePath(uploadFileName);

    faceReview.setUser(user);
    faceReview.setShop(shopService.findById(shopId));

    return faceReviewRepository.save(faceReview);
  }
}
