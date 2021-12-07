package com.one.eatmotion.controller;

import com.one.eatmotion.service.SentimentService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class SentimentController {

  private final SentimentService sentimentService;

  // test
  /**
   * @param text
   * @return double positive
   *     <p>JSONObject에서 "positive 만 뽑아서 리턴
   */
  @GetMapping("/sentiment")
  public HashMap<String, Double> sentiment(@RequestParam String text) {
    JSONObject jsonObject = new JSONObject(sentimentService.sentiment(text));
    JSONObject document = jsonObject.getJSONObject("document");
    JSONObject confidence = document.getJSONObject("confidence");
    double positiveDouble = confidence.getDouble("positive");
    HashMap<String, Double> positive = new HashMap<>();
    positive.put("positive : ", positiveDouble);
    return positive;
  }
}
