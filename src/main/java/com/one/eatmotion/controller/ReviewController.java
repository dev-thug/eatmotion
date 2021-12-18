package com.one.eatmotion.controller;

import com.one.eatmotion.entity.review.FaceReview;
import com.one.eatmotion.entity.review.TextReview;
import com.one.eatmotion.service.FaceReviewService;
import com.one.eatmotion.service.NaverFaceRecognition;
import com.one.eatmotion.service.NaverSentiment;
import com.one.eatmotion.service.TextReviewService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReviewController {

  private final TextReviewService textReviewService;
  private final FaceReviewService faceReviewService;

  @GetMapping("/{shopId}/review")
  public List<TextReview> listReview(@PathVariable Long shopId) {
    return textReviewService.findAllByShopId(shopId);
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "authToken",
        value = "허가된 유요한 토큰",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @PostMapping("/{shopId}/textReview")
  public TextReview saveReview(@RequestBody String content, @PathVariable Long shopId) {
    return textReviewService.saveTextReview(content, shopId);
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "authToken",
        value = "허가된 유요한 토큰",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @PostMapping("/{shopId}/faceReview")
  public FaceReview saveFaceReview(MultipartFile uploadFile, @PathVariable Long shopId)
      throws Exception {
    if (uploadFile != null) {

      UUID uuid = UUID.randomUUID();

      String property = System.getProperty("user.dir");

      String uploadFileName =
          property
              + "/src/main/resources/upload/faceReview/"
              + uuid
              + "_"
              + uploadFile.getOriginalFilename();

      File f = new File(uploadFileName);
      uploadFile.transferTo(f);

      return faceReviewService.save(shopId, uploadFileName);
    }
    throw new Exception("에러가 발생하였습니다");
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "authToken",
        value = "허가된 유요한 토큰",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @PutMapping("/{shopId}/review")
  public TextReview updateReview(@PathVariable Long shopId, Long id, String content) {
    return textReviewService.updateTextReview(id, content);
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "authToken",
        value = "허가된 유요한 토큰",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @DeleteMapping("/{shopId}/review")
  public void deleteReview(@PathVariable Long shopId, Long id) {
    textReviewService.deleteById(id);
  }
}
