package com.one.eatmotion.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.one.eatmotion.config.security.TokenProvider;
import com.one.eatmotion.entity.Shop;
import com.one.eatmotion.repository.ShopRepository;
import com.one.eatmotion.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OcrService {

    private final ShopRepository shopRepository;

    @Value("${api.secretKey}")
    private String secretKey;

    public String ocrCheck(File uploadFile) {
        String apiURL = "https://c1eef2138bd949eaa6d2e567b58496e6.apigw.ntruss.com/custom/v1/12637/58e63b10a12eba71c3d7aaba03f0defcc0b2428adb8565faedf5858ac898c164/infer";
        String imageFile = uploadFile.getAbsolutePath();

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setReadTimeout(30000);
            con.setRequestMethod("POST");
            String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-OCR-SECRET", secretKey);

            JSONObject json = new JSONObject();
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", "jpg");
            image.put("name", "test_ocr");
            JSONArray images = new JSONArray();
            images.put(image);
            json.put("images", images);
            String postParams = json.toString();

            con.connect();
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            long start = System.currentTimeMillis();
            File file = new File(imageFile);
            writeMultiPart(wr, postParams, file, boundary);
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            JSONObject jo = new JSONObject(response.toString());
            JSONArray ja = (JSONArray) jo.get("images");
            JSONObject jo2 = (JSONObject) ja.get(0);
            JSONArray ja2 = (JSONArray) jo2.get("fields");
            JSONObject jo3 = (JSONObject) ja2.get(2);
            System.out.println(jo3.get("inferText"));
            return jo3.get("inferText").toString();

        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
    }

    public String ocrSave(File uploadFile) {
        String apiURL = "https://c1eef2138bd949eaa6d2e567b58496e6.apigw.ntruss.com/custom/v1/12637/58e63b10a12eba71c3d7aaba03f0defcc0b2428adb8565faedf5858ac898c164/infer";
        String secretKey = "WWJtb3lmRHZuYmxjbXRFT2xocmZZREtRSFFReUxVUXM=";
        String imageFile = uploadFile.getAbsolutePath();

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setReadTimeout(30000);
            con.setRequestMethod("POST");
            String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-OCR-SECRET", secretKey);

            JSONObject json = new JSONObject();
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", "jpg");
            image.put("name", "test_ocr");
            JSONArray images = new JSONArray();
            images.put(image);
            json.put("images", images);
            String postParams = json.toString();

            con.connect();
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            long start = System.currentTimeMillis();
            File file = new File(imageFile);
            writeMultiPart(wr, postParams, file, boundary);
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            JSONObject jo = new JSONObject(response.toString());
            JSONArray ja = (JSONArray) jo.get("images");
            JSONObject jo2 = (JSONObject) ja.get(0);
            JSONArray ja2 = (JSONArray) jo2.get("fields");
            JSONObject jo3 = (JSONObject) ja2.get(0);
            JSONObject jo4 = (JSONObject) ja2.get(1);
            JSONObject jo5 = (JSONObject) ja2.get(2);
            JSONArray ja3 = (JSONArray) jo4.get("subFields");
            JSONObject jo6 = (JSONObject) ja3.get(1);
            shopRepository.save(Shop.builder().name(jo3.get("inferText").toString()).address(jo4.get("inferText").toString()).jachi(jo6.get("inferText").toString()).foodClassific(jo5.get("inferText").toString()).build());
            //System.out.println();
            return jo4.get("inferText").toString();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private static JSONObject geoCode(String address) throws Exception {

        String addr = URLEncoder.encode(address, "utf-8");
        String api = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + addr;
        StringBuffer sb = new StringBuffer();

        try {
            URL url = new URL(api);
            HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "발급받은 클라이언트 아이디");
            http.setRequestProperty("X-NCP-APIGW-API-KEY", "발급받은 클라이언트 키");
            http.setRequestMethod("GET");
            http.connect();

            InputStreamReader in = new InputStreamReader(http.getInputStream(), "utf-8");
            BufferedReader br = new BufferedReader(in);

            String inputLine;
            if (br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                JSONObject jo = new JSONObject(response.toString());
                JSONArray ja = (JSONArray) jo.get("addresses");

                /*
                 * for(int i=0; i<ja.length();i++) { JSONObject jo2 = (JSONObject) ja.get(i);
                 * if(null != jo2.get("x")) { x = jo2.get("x").toString(); } if(null !=
                 * jo2.get("y")) { y = jo2.get("y").toString(); } }
                 */
                System.out.println(response.toString());
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return null;
    }

    private static void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary) throws
            IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("--").append(boundary).append("\r\n");
        sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
        sb.append(jsonMessage);
        sb.append("\r\n");

        out.write(sb.toString().getBytes("UTF-8"));
        out.flush();

        if (file != null && file.isFile()) {
            out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
            StringBuilder fileString = new StringBuilder();
            fileString
                    .append("Content-Disposition:form-data; name=\"file\"; filename=");
            fileString.append("\"" + file.getName() + "\"\r\n");
            fileString.append("Content-Type: application/octet-stream\r\n\r\n");
            out.write(fileString.toString().getBytes("UTF-8"));
            out.flush();

            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[8192];
                int count;
                while ((count = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                }
                out.write("\r\n".getBytes());
            }

            out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
        }
        out.flush();
    }
}
