package com.one.eatmotion.controller;

import com.one.eatmotion.service.SentimentService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SentimentController {

	private final SentimentService sentimentService;


	/**
	 *
	 * @param text
	 * @return double positive
	 *
	 *  JSONObject에서 "positive 만 뽑아서 리턴
	 */
	@GetMapping("/sentiment")
	public double sentiment(@RequestParam String text) {
		JSONObject jsonObject = new JSONObject(sentimentService.sentiment(text));
		JSONObject document = jsonObject.getJSONObject("document");
		JSONObject confidence = document.getJSONObject("confidence");
		double positive = confidence.getDouble("positive");
		return positive;
	}
}
