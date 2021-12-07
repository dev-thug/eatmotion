package com.one.eatmotion.controller;

import com.one.eatmotion.entity.Shop;
import com.one.eatmotion.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Api(tags = {"3, 음식점"})
@RestController
@RequiredArgsConstructor
@Validated
public class ShopController {

  private final ShopService shopService;

  @ApiOperation(value = "음식점 검색", notes = "음식점을 검색합니다. ")
  @GetMapping("/shop/search")
  public List<Shop> shopFindAll() {
    return shopService.findAll();
  }
  /**
   * @param keyword
   * @return 키워드로 검색한 후 거리 내의 매장을 출력하기 위해 select를 다중으로 만들어야 함 지역구분 나눠서 entity로 추가한후 select 하는 방법,
   *     좌표(x, y) 계산 공부해서 거리 계산한 후 select 하는 방법 등 keyword도 2글자 이상으로 유효성 체크해야할 듯
   */
  @GetMapping("/shop/search/{keyword}")
  public List<Shop> shopSearchByKeyword(
      @Size(min = 2, message = "2글자 이상 입력해주세요") @PathVariable String keyword,
      @PathVariable int x,
      @PathVariable int y,
      // 사용자 지도 배율
      @PathVariable int meter
      ) {
    System.out.println(shopService.findBynameContaining(keyword));
    return shopService.findBynameContaining(keyword);
  }
  // 사용자 좌표 (x, y)

  @PostMapping("/shop/temp")
  public Shop shopTemp(@RequestBody Shop shop) {
    return shopService.shopTemp(shop);
  }
}
