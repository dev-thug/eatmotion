package com.one.eatmotion.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class SentimentService {

  final String clientId = "5eu50cwdvd"; // 애플리케이션 클라이언트 아이디값";
  final String clientSecret = "g42Rfm6qnHA2A8E2Ypjmeo4A7Hq8c8eVXYlAh3Un"; // 애플리케이션 클라이언트 시크릿값";

  public String sentiment(String text) {
    try {
      JSONObject jo = new JSONObject();
      jo.put("content", text);
      String requestBody = jo.toString();

      String apiURL = "https://naveropenapi.apigw.ntruss.com/sentiment-analysis/v1/analyze";
      URL url = new URL(apiURL);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("POST");
      con.setRequestProperty("Content-Type", "application/json; utf-8");
      con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
      con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

      // post request

      con.setDoOutput(true);
      DataOutputStream wr = new DataOutputStream(con.getOutputStream());
      wr.writeChars(requestBody);
      wr.flush();
      wr.close();
      int responseCode = con.getResponseCode();
      BufferedReader br;
      if (responseCode == 200) { // 정상 호출
        br = new BufferedReader(new InputStreamReader(con.getInputStream()));

      } else { // 오류 발생
        System.out.println("error!!!!!!! responseCode= " + responseCode);
        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      }
      String inputLine;
      if (br != null) {
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
          response.append(inputLine);
        }
        br.close();

        JSONObject jsonObject = new JSONObject(response.toString());
        JSONObject document = jsonObject.getJSONObject("document");
        JSONObject confidence = document.getJSONObject("confidence");
        double positiveDouble = confidence.getDouble("positive");

        return String.valueOf(positiveDouble);
      }

    } catch (Exception e) {
      System.out.println(e);
    }
    return "false";
  }
}
