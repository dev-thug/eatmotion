package com.one.eatmotion.repository;

import com.one.eatmotion.config.distance.Distance;
import com.one.eatmotion.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Todo: 모든 find method들 orderby평점, limit 개수 걸어야함, Review entity에 평점 만들고 Rank entity에 ManyToOne 관계
 * 지정해서 평균 낸 다음 평균으로 orderby 해야 할 듯
 */
public interface ShopRepository extends JpaRepository<Shop, Long> {

  List<Shop> findByNameContaining(String keword);

  /** Todo: 점수 순으로 n개 limit 해야함, pageable 사용 평점순 */
  @Query(
      value =
          "select s from Shop s where (:x BETWEEN :x-:meter and :x+:meter) and (:y BETWEEN :y-:meter and :y+:meter)")
  List<Shop> findShopByCoordinates(
      @Param("x") Double x, @Param("y") Double y, @Param("meter") Double meter);

  List<Shop> findShopByFoodClassific(String foodClassific);
}
