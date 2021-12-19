package com.one.eatmotion.controller;

import com.one.eatmotion.entity.Shop;
import com.one.eatmotion.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Api(tags = {"5. 맛집"})
@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @ApiOperation(value = "이름으로 음식점 검색", notes = "이름으로 음식점을 검색합니다.")
    @GetMapping("/shop/name/{keyword}")
    public List<Shop> findByNameContaining(
            @Size(min = 2, message = "2글자 이상 입력해주세요") @PathVariable String keyword) {
        System.out.println(shopService.findByNameContaining(keyword));
        return shopService.findByNameContaining(keyword);
    }

    @ApiOperation(value = "음식의 분류로 음식점을 검색", notes = "음식의 분류로 음식점을 검색합니다.")
    @GetMapping("/shop/part/{classification}")
    public List<Shop> findShopByFoodClassific(@PathVariable String classification) {
        return shopService.findShopByFoodClassific(classification);
    }


    /**
     * @param userX    사용자의 현재 x 좌표
     * @param userY    사용자의 현재 y 좌표
     * @param distance KM 단위
     * @return List<Shop> distance안의 음식점들을 평점순으로 제한된 갯수만큼 반환
     */
    @GetMapping("/shop/gps")
    @ApiOperation(value = "사용자 주변 음식점", notes = "사용자 주변의 음식점의 좌표를 반환합니다.")
    public List<Shop> findShopByCoordinates(Double userX, Double userY, Double distance) {
        return shopService.findShopByCoordinates(userX, userY, distance);
    }


//    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "음식점 추가", notes = "[테스트]")
    @PostMapping("/shop")
    public Shop shopAdd(@RequestBody Shop shop) {
        return shopService.shopTemp(shop);
    }

//    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "평점 추가", notes = "[테스트]평점 수동 추가")
    @PostMapping("/shop/grade/{id}")
    public void shopTemp2(@PathVariable Long id) {
        shopService.updateShopGrade(id);
    }
}
