package com.one.eatmotion.service;

import com.one.eatmotion.config.distance.Distance;
import com.one.eatmotion.entity.Shop;
import com.one.eatmotion.repository.ShopRepository;
import com.one.eatmotion.repository.TextReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopService {

  private final ShopRepository shopRepository;
  private final Distance distance;
  private final TextReviewRepository textReviewRepository;

  @Transactional
  public void updateShopGrade(Long id) {
    Shop shop = shopRepository.findById(id).orElseThrow();
    ArrayList<Double> shopGradeList = textReviewRepository.findGradeByShopId(id);
    Double shopGradeSum = shopGradeList.stream().mapToDouble(i -> i).sum();
    Double shopGrade = shopGradeSum / shopGradeList.size();
    shop.setGrade(shopGrade);
  }

  public Shop findById(Long id) {
    return shopRepository.findById(id).orElseThrow();
  }

  public List<Shop> findByNameContaining(String keyword) {
    return shopRepository.findByNameContaining(keyword);
  }

  @Transactional
  public Shop shopTemp(Shop shop) {
    return shopRepository.save(shop);
  }

  public List<Shop> findAll() {
    return shopRepository.findAll();
  }

  public List<Shop> findShopByCoordinates(Double userX, Double userY, Double distance) {
    //    double meter = distance.distance(x, y, userX, userY);
    return shopRepository.findShopByCoordinates(userX, userY, distance, PageRequest.of(0, 30));
  }

  public List<Shop> findShopByFoodClassific(String foodClassific) {
    return shopRepository.findShopByFoodClassific(foodClassific);
  }

  public List<Shop> findByAddress(String address) {
    return shopRepository.findByAddress(address);
  }
}
