package com.one.eatmotion.service;

import com.one.eatmotion.entity.Shop;
import com.one.eatmotion.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

  private final ShopRepository shopRepository;

  public List<Shop> findBynameContaining(String keyword) {
    return shopRepository.findBynameContaining(keyword);
  }

  public Shop shopTemp(Shop shop) {
    return shopRepository.save(shop);
  }

  public List<Shop> findAll() {
    return shopRepository.findAll();
  }

  public List<Shop> findShopByCoordinates(double x, double y, int meter) {
    return shopRepository.findShopByCoordinates(x, y, meter);
  }
}
