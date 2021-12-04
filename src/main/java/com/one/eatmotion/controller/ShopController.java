package com.one.eatmotion.controller;

import com.one.eatmotion.entity.Shop;
import com.one.eatmotion.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShopController {

	private final ShopService shopService;


	/**
	 * @param keyword
	 * @return 키워드로 검색한 후 거리 내의 매장을 출력하기 위해 select를 다중으로 만들어야 함
	 * 지역구분 나눠서 entity로 추가한후 select 하는 방법,
	 * 좌표(x, y) 계산 공부해서 거리 계산한 후 select 하는 방법 등
	 * keyword도 2글자 이상으로 유효성 체크해야할 듯
	 */
	@GetMapping("/shop/search/{keyword}")
	public List<Shop> shopSearchByKeyword(@PathVariable String keyword) {
		System.out.println(shopService.findBynameContaining(keyword));
		return shopService.findBynameContaining(keyword);
	}

	@PostMapping("/shop/temp")
	public Shop shopTemp(@RequestBody Shop shop) {
		return shopService.shopTemp(shop);
	}
}
