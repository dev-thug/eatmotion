package com.one.eatmotion.service;

import com.one.eatmotion.advice.exception.ReserveTimeOverException;
import com.one.eatmotion.entity.Reserve;
import com.one.eatmotion.entity.Shop;
import com.one.eatmotion.entity.User;
import com.one.eatmotion.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.nio.channels.AcceptPendingException;
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

    User user = userService.getAuthedUser();
    if (!Objects.equals(user.getId(), reserveRepository.getById(id).getUser().getId())) {
      throw new AccessDeniedException("접근이 권한이 없습니다.");
    }

    return reserveRepository.getDetailListById(id);
  }

  public Reserve updateReserve(Long id, Reserve reserve) {
    Reserve updatedReserve = reserveRepository.getDetailListById(id);

    User user = userService.getAuthedUser();
    if (!Objects.equals(user.getId(), updatedReserve.getUser().getId())) {
      throw new AccessDeniedException("접근이 권한이 없습니다.");
    }

    // 예약 시간 - now 2시간 전까지만 변경할 수 있도록
    LocalDateTime reserveTime = reserveRepository.getDetailListById(id).getReserveDateTime();
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

  public void deleteReserve(Long id) {

    Reserve reserve = reserveRepository.findById(id).orElseThrow();

    User user = userService.getAuthedUser();
    if (!Objects.equals(user.getId(), reserve.getUser().getId())) {
      throw new AccessDeniedException("접근이 권한이 없습니다.");
    }

    // 예약 시간 - now 2시간 전까지만 취소할 수 있도록
    LocalDateTime reserveTime = reserveRepository.getDetailListById(id).getReserveDateTime();
    LocalDateTime now = LocalDateTime.now();

    Duration duration = Duration.between(now, reserveTime);

    if (duration.getSeconds() < 7200) {
      reserveRepository.deleteById(id);
    } else {
      throw new ReserveTimeOverException();
    }
  }
}
