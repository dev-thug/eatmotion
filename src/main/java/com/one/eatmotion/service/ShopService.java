package com.one.eatmotion.service;

import com.one.eatmotion.config.distance.Distance;
import com.one.eatmotion.entity.Shop;
import com.one.eatmotion.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

  private final ShopRepository shopRepository;

  private final Distance distance;

  public List<Shop> findByNameContaining(String keyword) {
    return shopRepository.findByNameContaining(keyword);
  }

  public Shop shopTemp(Shop shop) {
    return shopRepository.save(shop);
  }

  public List<Shop> findAll() {
    return shopRepository.findAll();
  }

  public List<Shop> findShopByCoordinates(Double userX, Double userY) {
    //    double meter = distance.distance(x, y, userX, userY);
    return shopRepository.findShopByCoordinates(userX, userY);
  }

  public List<Shop> findShopByFoodClassific(String foodClassific) {
    return shopRepository.findShopByFoodClassific(foodClassific);
  }
  
  public List<Shop> findByAddress(String address){
	  return shopRepository.findByAddress(address);
  }
}
