package com.one.eatmotion.repository;

import com.one.eatmotion.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {

  @Query("SELECT b FROM Post b WHERE b.content like %?1% and b.title > 0 ORDER BY b.id desc")
  List<Shop> findBynameContaining(String keword);

  /**
   * @param x
   * @param y
   * @param meter <<파라미터 meter 바꿔야됨
   * @return
   */

  // "select s from Shop s where (:x BETWEEN :x-:meter and :x+:meter) and (:y BETWEEN :y-:meter and
  // :y+:meter)"
  @Query(
      value =
          "select s from Shop s where (:x BETWEEN :x-:meter and :x+:meter) and (:y BETWEEN :y-:meter and :y+:meter)")
  List<Shop> shopSearchByCoordinates(
      @Param("x") Double x, @Param("y") Double y, @Param("meter") Integer meter);
}
