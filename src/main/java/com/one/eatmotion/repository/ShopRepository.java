package com.one.eatmotion.repository;

import com.one.eatmotion.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {

  // x 3 y 3
  // 1
  //
  // (keyword , x , y , 범위)


  List<Shop> findBynameContaining(String keword);
}
