package com.one.eatmotion.repository;

import com.one.eatmotion.config.distance.Distance;
import com.one.eatmotion.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * Todo: 모든 find method들 orderby평점, limit 개수 걸어야함, Review entity에 평점 만들고 Rank entity에 ManyToOne 관계
 * 평점을 shop에 놓고 계속 갱신 지정해서 평균 낸 다음 평균으로 orderby 해야 할 듯
 */
public interface ShopRepository extends JpaRepository<Shop, Long> {

  List<Shop> findByNameContaining(String keword);
  
  List<Shop> findByAddress(String address);
  /** Todo: 점수 순으로 n개 limit 해야함, pageable 사용 평점순 */

  // Distance.distance(x, y, userX, userY) <= 5000    //(meter == 5000)
  // 거리계산하는 쿼리
  //  @Query(
  //      value =
  //          "select s from Shop s where (:x BETWEEN :x-:meter and :x+:meter) and (:y BETWEEN
  // :y-:meter and :y+:meter)")
  //  List<Shop> findShopByCoordinates(
  //      @Param("x") Double x, @Param("y") Double y, @Param("meter") Double meter);

  @Query(
      value =
          "SELECT id, ( 6371 * acos ( cos ( radians( :userY ) ) * cos( radians( y ) )  * cos( radians( x ) - radians(:userX) )  + sin ( radians(:userY) ) * sin( radians( Y ) )  ) AS distance FROM Shop HAVING distance < 5 ORDER BY distance LIMIT 0 , 20;",
      nativeQuery = true)
  List<Shop> findShopByCoordinates(@Param("userX") Double userX, @Param("userY") Double userY);

  List<Shop> findShopByFoodClassific(String foodClassific);
}
