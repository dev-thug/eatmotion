package com.one.eatmotion.service;

import com.one.eatmotion.entity.Reserve;
import com.one.eatmotion.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
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

  public Reserve updateReserve(Long reserveId, Reserve reserve) {
    Reserve updatedReserve = reserveRepository.getDetailListById(reserveId);

    // 예약 시간 - now 2시간 전까지만 변경할 수 있도록
    LocalDateTime reserveTime = reserveRepository.getDetailListById(reserveId).getReserveDateTime();
    LocalDateTime now = LocalDateTime.now();

    Duration duration = Duration.between(now, reserveTime);

    if (duration.getSeconds() < 7200) {
      updatedReserve.setReserveDateTime(reserve.getReserveDateTime());
      updatedReserve.setReserveNumberOfPeople(reserve.getReserveNumberOfPeople());
      return updatedReserve;
    } else {
      return null;
    }
  }

  public void deleteReserve(Long reserveId) {
    // 예약 시간 - now 2시간 전까지만 취소할 수 있도록
    LocalDateTime reserveTime = reserveRepository.getDetailListById(reserveId).getReserveDateTime();
    LocalDateTime now = LocalDateTime.now();

    Duration duration = Duration.between(now, reserveTime);

    if (duration.getSeconds() < 7200) {
      reserveRepository.deleteById(reserveId);
    }
  }
}
