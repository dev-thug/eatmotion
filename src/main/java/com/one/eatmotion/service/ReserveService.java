package com.one.eatmotion.service;

import com.one.eatmotion.advice.exception.ReserveTimeOverException;
import com.one.eatmotion.entity.Reserve;
import com.one.eatmotion.entity.Shop;
import com.one.eatmotion.entity.User;
import com.one.eatmotion.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReserveService {

  private final ReserveRepository reserveRepository;
  private final UserService userService;
  private final ShopService shopService;

  public List<Reserve> getListByUserId(Long userId) {
    return reserveRepository.getListByUserId(userId);
  }

  public Reserve makeReserve(Reserve reserve, Long shopId) {
    User user = userService.getAuthedUser();
    Shop shop = shopService.findById(shopId);
    reserve.setShop(shop);
    reserve.setUser(user);
    return reserveRepository.save(reserve);
  }

  public Reserve getDetailListByReserveId(Long id) {
    return reserveRepository.getDetailListById(id);
  }

  public Reserve updateReserve(Long reserveId, Reserve reserve) throws Exception {
    Reserve updatedReserve = reserveRepository.getDetailListById(reserveId);

    User user = userService.getAuthedUser();
    if (!Objects.equals(user.getId(), updatedReserve.getUser().getId())) {
      throw new Exception();
    }

    // 예약 시간 - now 2시간 전까지만 변경할 수 있도록
    LocalDateTime reserveTime = reserveRepository.getDetailListById(reserveId).getReserveDateTime();
    LocalDateTime now = LocalDateTime.now();

    Duration duration = Duration.between(now, reserveTime);

    if (duration.getSeconds() < 7200) {
      updatedReserve.setReserveDateTime(reserve.getReserveDateTime());
      updatedReserve.setReserveNumberOfPeople(reserve.getReserveNumberOfPeople());
      return updatedReserve;
    } else {
      throw new ReserveTimeOverException();
    }
  }

  public void deleteReserve(Long reserveId) throws Exception {

    Reserve reserve = reserveRepository.findById(reserveId).orElseThrow();

    User user = userService.getAuthedUser();
    if (!Objects.equals(user.getId(), reserve.getUser().getId())) {
      throw new Exception();
    }

    // 예약 시간 - now 2시간 전까지만 취소할 수 있도록
    LocalDateTime reserveTime = reserveRepository.getDetailListById(reserveId).getReserveDateTime();
    LocalDateTime now = LocalDateTime.now();

    Duration duration = Duration.between(now, reserveTime);

    if (duration.getSeconds() < 7200) {
      reserveRepository.deleteById(reserveId);
    } else {
      throw new ReserveTimeOverException();
    }
  }
}
