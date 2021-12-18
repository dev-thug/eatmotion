package com.one.eatmotion.controller;

import com.one.eatmotion.dto.ReserveDTO;
import com.one.eatmotion.entity.Reserve;
import com.one.eatmotion.service.ReserveService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReserveController {

  private final ReserveService reserveService;

  /** 매장 상세 페이지에서 시작한다고 가정 */
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "authToken",
        value = "허가된 유요한 토큰",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @PostMapping("/reserve/{shopId}")
  public Reserve makeReserve(@PathVariable Long shopId, @RequestBody ReserveDTO reserve) {
    return reserveService.makeReserve(reserve, shopId);
  }

  /** 예약 리스트 부분 */
  @GetMapping("/reserve/{userId}")
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "authToken",
        value = "허가된 유요한 토큰",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  public List<Reserve> listReserve(@PathVariable Long userId) {
    System.out.println("try listReserve/{userId}");
    return reserveService.getListByUserId(userId);
  }

  /** 예약 상세정보 확인하는 부분 */
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "authToken",
        value = "허가된 유요한 토큰",
        required = true,
        dataType = "String",
        paramType = "header")
  })
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
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "authToken",
        value = "허가된 유요한 토큰",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @PutMapping("/reserve/{id}")
  public Reserve editReserve(@PathVariable Long id, @RequestBody ReserveDTO reserve) {

    return reserveService.updateReserve(id, reserve);
  }

  /** 예약 취소하는 부분 */
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "authToken",
        value = "허가된 유요한 토큰",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @DeleteMapping("/reserve/{reserveId}")
  public void deleteReserve(@PathVariable Long reserveId, Reserve reserve) {
    System.out.println("try deleteReserve");
    reserveService.deleteReserve(reserveId);
  }
}
