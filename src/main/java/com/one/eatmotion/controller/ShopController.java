package com.one.eatmotion.controller;

import com.one.eatmotion.entity.Shop;
import com.one.eatmotion.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ShopController {

  private final ShopService shopService;

  @GetMapping("/shop/search/a/{keyword}")
  public List<Shop> findByNameContaining(
      @Size(min = 2, message = "2글자 이상 입력해주세요") @PathVariable String keyword) {
    System.out.println(shopService.findByNameContaining(keyword));
    return shopService.findByNameContaining(keyword);
  }

  /**
   * @param userX
   * @param userY
   * @param distance distance는 KM 단위로 받습니다
   */
  @GetMapping("/shop/search/gps")
  public List<Shop> findShopByCoordinates(Double userX, Double userY, Double distance) {
    return shopService.findShopByCoordinates(userX, userY, distance);
  }

  @GetMapping("/shop/search/{foodClassific}")
  public List<Shop> findShopByFoodClassific(@PathVariable String foodClassific) {
    return shopService.findShopByFoodClassific(foodClassific);
  }

  @PostMapping("/shop/temp")
  public Shop shopTemp(@RequestBody Shop shop) {
    return shopService.shopTemp(shop);
  }

  @PostMapping("/shop/temp2")
  public void shopTemp2(Long id) {
    shopService.updateShopGrade(id);
  }
}
