package com.one.eatmotion.service;

import com.one.eatmotion.entity.review.Review;
import com.one.eatmotion.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
}
