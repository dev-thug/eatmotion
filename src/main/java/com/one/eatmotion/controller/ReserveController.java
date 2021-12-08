package com.one.eatmotion.controller;

import com.one.eatmotion.entity.Reserve;
import com.one.eatmotion.service.ReserveService;
import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReserveController {

  private final ReserveService reserveService;

  /** 매장 상세 페이지에서 시작한다고 가정 */
  @PostMapping("/reserve/{storeId}")
  public Reserve makeReserve(@PathVariable Long storeId, @RequestBody Reserve reserve) {
    System.out.println("try reserve/{id}");
    //		reserveService.makeReserve(reserve);
    return reserveService.makeReserve(reserve);
  }

  /** 예약 리스트 부분 */
  @GetMapping("/reserve/{userId}")
  public List<Reserve> listReserve(@PathVariable Long userId) {
    System.out.println("try listReserve/{userId}");
    return reserveService.getListByUserId(userId);
  }

  /** 예약 상세정보 확인하는 부분 */
  @GetMapping("/reserve/detail/{id}")
  public Reserve listDetailReserve(@PathVariable Long id) {
    System.out.println("try listReserve/{reserveId}");
    return reserveService.getDetailListByReserveId(id);
  }

  /**
   * 예약 변경하는 부분
   *
   * <p>reserveDate랑 reserveNumberOfPeople만 바꿀 수 있음 (임시)
   */
  // TODO: 예약 n 시간 전까지 변경할 수 있다 라는 조건 걸어야함
  @PutMapping("/reserve/{id}")
  public Reserve editReserve(@PathVariable Long id, @RequestBody Reserve reserve) {
    System.out.println("try editReserve/{reserveId}");

    Reserve oldReserve = reserveService.getDetailListByReserveId(id);

    oldReserve.setReserveDate(reserve.getReserveDate());
    oldReserve.setReserveNumberOfPeople(reserve.getReserveNumberOfPeople());

    return reserveService.getDetailListByReserveId(id);
  }

  /** 예약 취소하는 부분 */
  // TODO: 예약 n 시간 전까지 취소할 수 있다 라는 조건 걸어야함
  @DeleteMapping("/reserve/{reserveId}")
  public void deleteReserve(@PathVariable Long reserveId) {
    System.out.println("try deleteReserve");
    reserveService.deleteReserve(reserveId);
  }
}
