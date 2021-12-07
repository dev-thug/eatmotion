package com.one.eatmotion.service;

import com.one.eatmotion.entity.Reserve;
import com.one.eatmotion.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReserveService {

  private final ReserveRepository reserveRepository;

  public List<Reserve> getListByUserId(Long userId) {
    return reserveRepository.getListByUserId(userId);
  }

  public Reserve makeReserve(Reserve reserve) {
    return reserveRepository.save(reserve);
  }

  public Reserve getDetailListByReserveId(Long id) {
    return reserveRepository.getDetailListById(id);
  }

  public void deleteReserve(Long reserveId) {
    reserveRepository.deleteById(reserveId);
  }
}
