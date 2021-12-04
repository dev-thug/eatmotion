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

	@GetMapping("/shop/search/{keyword}")
	public List<Shop> shopSearch(@PathVariable String keyword) {
		System.out.println(shopService.findBynameContaining(keyword));
		return shopService.findBynameContaining(keyword);
	}

	@PostMapping("/shop/temp")
	public Shop shopTemp(@RequestBody Shop shop) {
		return shopService.shopTemp(shop);
	}
}
