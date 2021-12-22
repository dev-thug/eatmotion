package com.one.eatmotion.service.naverApi;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Service
// 네이버 얼굴인식 API 예제

public class NaverFaceRecognition {

  @Value("${api.id}")
  private String clientId; // 애플리케이션 클라이언트 아이디값";

  @Value("${api.secret}")
  private String clientSecret;

  public HashMap<String, String> face(String imgFile) throws IOException {

    String paramName = "image"; // 파라미터명은 image로 지정
    //            String imgFile = "이미지 파일 경로 ";
    File uploadFile = new File(imgFile);
    String apiURL = "https://naveropenapi.apigw.ntruss.com/vision/v1/face"; // 얼굴 감지
    URL url = new URL(apiURL);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setUseCaches(false);
    con.setDoOutput(true);
    con.setDoInput(true);
    // multipart request
    String boundary = "---" + System.currentTimeMillis() + "---";
    con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
    con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
    con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
    OutputStream outputStream = con.getOutputStream();
    PrintWriter writer =
        new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true);
    String LINE_FEED = "\r\n";
    // file 추가
    String fileName = uploadFile.getName();
    writer.append("--" + boundary).append(LINE_FEED);
    writer
        .append(
            "Content-Disposition: form-data; name=\""
                + paramName
                + "\"; filename=\""
                + fileName
                + "\"")
        .append(LINE_FEED);
    writer
        .append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName))
        .append(LINE_FEED);
    writer.append(LINE_FEED);
    writer.flush();
    FileInputStream inputStream = new FileInputStream(uploadFile);
    byte[] buffer = new byte[4096];
    int bytesRead = -1;
    while ((bytesRead = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, bytesRead);
    }
    outputStream.flush();
    inputStream.close();
    writer.append(LINE_FEED).flush();
    writer.append("--" + boundary + "--").append(LINE_FEED);
    writer.close();
    BufferedReader br = null;
    int responseCode = con.getResponseCode();
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

      // Json 파싱하는 부분
      JSONObject jsonObject = new JSONObject(response.toString());
      JSONArray jsonArray = jsonObject.getJSONArray("faces");
      JSONObject answer = jsonArray.getJSONObject(0);
      JSONObject emotionJsonObject = answer.getJSONObject("emotion");
      String emotion = emotionJsonObject.getString("value");
      JSONObject ageJsonObject = answer.getJSONObject("age");
      String age = ageJsonObject.getString("value");

      HashMap<String, String> faceResult = new HashMap<>();
      faceResult.put("emotion", emotion);
      faceResult.put("age", age);

      return faceResult;
    } else {
      System.out.println("error !!!");
    }
    return null;
  }
}
