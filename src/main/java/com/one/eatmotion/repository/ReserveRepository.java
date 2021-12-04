package com.one.eatmotion.repository;

import com.one.eatmotion.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

	/*
	 *  리스트 만들어고싶음 where userID = {userId}로
	 * */
	List<Reserve> getListByUserId(Long userId);

	Reserve getDetailListById(Long id);

}
