package com.one.eatmotion.repository;

import com.one.eatmotion.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

  List<Reserve> getListByUserId(Long userId);

  Reserve getDetailListById(Long id);
}
