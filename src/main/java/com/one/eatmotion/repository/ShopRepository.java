package com.one.eatmotion.repository;

import com.one.eatmotion.entity.Shop;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Todo: 모든 find method들 orderby평점, limit 개수 걸어야함, Review entity에 평점 만들고 Rank entity에 ManyToOne 관계
 * 평점을 shop에 놓고 계속 갱신 지정해서 평균 낸 다음 평균으로 orderby 해야 할 듯
 */
public interface ShopRepository extends JpaRepository<Shop, Long> {

  List<Shop> findByNameContaining(String keword);

  List<Shop> findByAddress(String address);

  /** Todo: 점수 순으로 n개 limit 해야함, pageable 사용 평점순 */
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

  default List<Shop> findTop30ShopByCoordinates(
      Double userX, Double userY, Double distance, Pageable pageable) {
    return findShopByCoordinates(userX, userY, distance, PageRequest.of(0, 30));
  }

  List<Shop> findShopByFoodClassific(String foodClassific);
}
