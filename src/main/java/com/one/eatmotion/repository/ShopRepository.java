package com.one.eatmotion.repository;

import com.one.eatmotion.entity.Shop;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/** 50개, 점수순으로 나오는 상황 */
public interface ShopRepository extends JpaRepository<Shop, Long> {

  List<Shop> findTop50ByNameContainingOrderByGrade(String keword);

  List<Shop> findTop50ByAddressOrderByGrade(String address);

  String HAVERSINE_PART =
      "(6371 * acos(cos(radians(:userY)) * cos(radians(y)) * cos(radians(x) - radians(:userX)) + sin(radians(:userY)) * sin(radians(y))))";

  @Query(
      "SELECT s FROM Shop s WHERE "
          + HAVERSINE_PART
          + " < :distance ORDER BY "
          + HAVERSINE_PART
          + " ASC")
  List<Shop> findShopByCoordinates(
      @Param("userX") Double userX,
      @Param("userY") Double userY,
      @Param("distance") Double distance,
      Pageable pageable);

  // 거리순으로 30개 나옴
  default List<Shop> findTop30ShopByCoordinates(
      Double userX, Double userY, Double distance, Pageable pageable) {
    return findShopByCoordinates(userX, userY, distance, PageRequest.of(0, 30));
  }

  List<Shop> findTop50ShopByFoodClassificOrderByGrade(String foodClassific);
}
