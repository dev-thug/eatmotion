package com.one.eatmotion.controller;

import com.one.eatmotion.advice.exception.NotFoundEmailException;
import com.one.eatmotion.entity.review.FaceReview;
import com.one.eatmotion.entity.review.TextReview;
import com.one.eatmotion.service.FaceReviewService;
import com.one.eatmotion.service.TextReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Api(tags = {"6. 음식점 리뷰 작성"})
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final TextReviewService textReviewService;
    private final FaceReviewService faceReviewService;

    @GetMapping("/reviews/{shopId}")
    @ApiOperation(value = "음식점에 대한 리뷰 조회", notes = "음식점에 대한 리뷰를 조회 합니다.")
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
    @PostMapping("/text-review/{shopId}")
    @ApiOperation(value = "텍스트 리뷰 작성", notes = "텍스트 리뷰 작성합니다.")
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
    @PostMapping(value = "/face-review/{shopId}")
    @ApiOperation(value = "사진 리뷰 작성", notes = "사진 리뷰 작성합니다.")
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
        throw new NotFoundEmailException();
    }


    // TODO: 2021-12-18 수정이 작동 확인
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "authToken",
                    value = "허가된 유요한 토큰",
                    required = true,
                    dataType = "String",
                    paramType = "header")
    })
    @PutMapping("/text-review/{shopId}")
    @ApiOperation(value = "텍스트 리뷰 수정", notes = "텍스트 리뷰 수정합니다.")
    public TextReview updateReview(@PathVariable Long shopId, @RequestBody String content) {
        return textReviewService.updateTextReview(shopId, content);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "authToken",
                    value = "허가된 유요한 토큰",
                    required = true,
                    dataType = "String",
                    paramType = "header")
    })
    @ApiOperation(value = "텍스트 리뷰 삭제", notes = "작성한 텍스트 리뷰를 삭제 합니다.")
    @DeleteMapping("/text-review/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        textReviewService.deleteById(reviewId);
    }
}
