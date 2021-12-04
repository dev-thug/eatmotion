package com.one.eatmotion.repository;

import com.one.eatmotion.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
	List<Shop> findBynameContaining(String keword);
}
