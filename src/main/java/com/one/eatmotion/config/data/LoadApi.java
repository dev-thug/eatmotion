// package com.one.eatmotion.config.data;
//
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.MalformedURLException;
// import java.net.URL;
// import java.util.ArrayList;
// import java.util.List;
//
// import org.json.JSONArray;
// import org.json.JSONObject;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import com.one.eatmotion.entity.Shop;
// import com.one.eatmotion.repository.ShopRepository;
//
// import lombok.RequiredArgsConstructor;
//
// @Configuration
// @RequiredArgsConstructor
// public class LoadApi {
//
//  @Bean
//  CommandLineRunner initData(ShopRepository shopRepository) throws Exception {
//    // Singleton
//    int cur = 1;
//    while (true) {
//      List<Shop> shopList = getShopList(cur, cur + 999);
//      if (shopList == null) break;
//
//      // shopRepository.save(null);
//      for (Shop shop : shopList) {
//        shopRepository.save(shop);
//        // System.out.println(shop);
//      }
//
//      cur += 1000;
//    }
//
//    /*
//     * for(int i=1; i<9233; i++) { URL url = new URL(
//     * "http://openapi.seoul.go.kr:8088/4d5641454c646d7336327a4253724b/json/CrtfcUpsoInfo/"
//     * +i+"/"+(i+999)+"/"); i= i+ 999; HttpURLConnection con = (HttpURLConnection)
//     * url.openConnection(); BufferedReader reader = new BufferedReader(new
//     * InputStreamReader(con.getInputStream())); String obj = reader.readLine();
//     * JSONObject jo=new JSONObject(obj);
//     *
//     * JSONObject jo1=(JSONObject) jo.get("CrtfcUpsoInfo"); JSONArray ja =
//     * (JSONArray) jo1.get("row"); for(int x=0;x<1000;x++) { JSONObject jo2=
//     * (JSONObject) ja.get(x); String shop_name = (String) jo2.get("UPSO_NM");
//     * String shop_jachi = (String) jo2.get("CGG_CODE_NM"); Double shop_x =
//     * Double.valueOf((String) jo2.get("X_CNTS")); Double shop_y =
//     * Double.valueOf((String) jo2.get("Y_DNTS")); Shop shop =
//     * shopRepository.save(Shop.builder().name(shop_name).x(shop_x).y(shop_y).build(
//     * )); System.out.println(shop); }
//     *
//     *
//     *
//     * //Upso upsoList = new ObjectMapper().readValue(obj, UpsoListDTO.Upso.class);
//     * //System.out.println(upsoList.toString()); //음식점 1개마다 객체를 만들라는 거?{1,2,3,4,5}
//     * //return upsoList.crtfcUpsoInfo; }
//     */
//    return null;
//  }
//
//  List<Shop> getShopList(int start, int end) throws Exception {
//    URL url =
//        new URL(
//            "http://openapi.seoul.go.kr:8088/4d5641454c646d7336327a4253724b/json/CrtfcUpsoInfo/"
//                + start
//                + "/"
//                + end
//                + "/");
//    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//    String obj = reader.readLine();
//    JSONObject jo = new JSONObject(obj);
//
//    JSONObject jo1 = new JSONObject();
//    if (jo.has("CrtfcUpsoInfo")) {
//      jo1 = (JSONObject) jo.get("CrtfcUpsoInfo");
//    } else if (jo.has("RESULT")) {
//      JSONObject result = (JSONObject) jo.get("RESULT");
//      if (result.getString("CODE").equals("INFO-200")) return null;
//    } else {
//      throw new Exception("error");
//    }
//
//    /*
//     * JSONObject jo1=(JSONObject) jo.get("CrtfcUpsoInfo");
//     * //System.out.println("jo1 ="+ jo1); if (jo.has("RESULT")) {
//     * System.out.println("for entered"); JSONObject result = (JSONObject)
//     * jo.get("RESULT"); if (result.get("CODE") == "INFO-200") return null; }
//     */
//
//    JSONArray ja = (JSONArray) jo1.get("row");
//
//    ArrayList<Shop> shopList = new ArrayList<Shop>();
//    for (Object o : ja) {
//      JSONObject jo2 = (JSONObject) o;
//      String shop_name = (String) jo2.get("UPSO_NM");
//      String shop_jachi = (String) jo2.get("CGG_CODE_NM");
//      String shop_address = (String) jo2.get("RDN_CODE_NM");
//      String shop_food_classific = (String) jo2.get("BIZCND_CODE_NM");
//      Double shop_x = Double.valueOf((String) jo2.get("X_CNTS"));
//      Double shop_y = Double.valueOf((String) jo2.get("Y_DNTS"));
//
//      Shop shop =
//          Shop.builder()
//              .name(shop_name)
//              .x(shop_x)
//              .y(shop_y)
//              .jachi(shop_jachi)
//              .address(shop_address)
//              .foodClassific(shop_food_classific)
//              .build();
//
//      shopList.add(shop);
//    }
//
//    return shopList;
//  }
// }
