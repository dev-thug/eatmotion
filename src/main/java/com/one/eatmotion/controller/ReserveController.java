package com.one.eatmotion.controller;

import com.one.eatmotion.dto.ReserveDTO;
import com.one.eatmotion.entity.Reserve;
import com.one.eatmotion.service.ReserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"4. 맛집 예약"})
@RestController
@RequiredArgsConstructor
public class ReserveController {

    private final ReserveService reserveService;

    /**
     * 매장 상세 페이지에서 시작한다고 가정
     */
    @ApiOperation(value = "예약 하기", notes = "회원이 음식점 예약을 한다.")
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

    /**
     * 예약 리스트 부분
     */
    @GetMapping("/reserves")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "authToken",
                    value = "허가된 유요한 토큰",
                    required = true,
                    dataType = "String",
                    paramType = "header")
    })
    @ApiOperation(value = "예약 조회", notes = "내가 예약한 음식점 리스트")
    public List<Reserve> listReserve() {
        System.out.println("try listReserve/{userId}");
        return reserveService.findAllByUser();
    }

    /**
     * 예약 상세정보 확인하는 부분
     */
    @ApiOperation(value = "예약 상세 조회", notes = "회원이 예약한 정보 상세 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "authToken",
                    value = "허가된 유요한 토큰",
                    required = true,
                    dataType = "String",
                    paramType = "header")
    })
    @GetMapping("/reserve/{id}")
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
    @ApiOperation(value = "예약 변경", notes = "회원이 자신이 예약한 정보를 수정")
    public Reserve editReserve(@PathVariable Long id, @RequestBody ReserveDTO reserve) {

        return reserveService.updateReserve(id, reserve);
    }

    /**
     * 예약 취소하는 부분
     */
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "authToken",
                    value = "허가된 유요한 토큰",
                    required = true,
                    dataType = "String",
                    paramType = "header")
    })
    @ApiOperation(value = "예약 취소", notes = "회원이 예약을 취소한다")
    @DeleteMapping("/reserve/{reserveId}")
    public void deleteReserve(@PathVariable Long reserveId) {
        System.out.println("try deleteReserve");
        reserveService.deleteReserve(reserveId);
    }
}
