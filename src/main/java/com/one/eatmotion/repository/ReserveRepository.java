package com.one.eatmotion.repository;

import com.one.eatmotion.entity.Reserve;
import com.one.eatmotion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

  List<Reserve> findAllByUser(User user);

  Reserve getDetailListById(Long id);


}
