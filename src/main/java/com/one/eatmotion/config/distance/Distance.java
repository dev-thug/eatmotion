package com.one.eatmotion.config.distance;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.one.eatmotion.config.distance"})
public class Distance {
  //		public static void main(String[] args) {
  //
  //				// 미터(Meter) 단위
  //				double distanceMeter =
  //								distance(37.493952396600605, 127.01425965805629, 37.49174338384156, 127.00820725341607);
  //
  //				System.out.println(distanceMeter) ;
  //		}

  // This function converts decimal degrees to radians
  private static double deg2rad(double deg) {
    return (deg * Math.PI / 180.0);
  }

  // This function converts radians to decimal degrees
  private static double rad2deg(double rad) {
    return (rad * 180 / Math.PI);
  }

  /**
   * 두 지점간의 거리 계산 main 실행확인 결과 from 교대역 to 서초역 587미터
   *
   * @param lat1 37.493952396600605, @param lon1 127.01425965805629 교대역
   * @param lat2 37.49174338384156, @param lon2127.00820725341607 서초역
   * @return 587.733778806214 (미터 계산)
   * @param lat1 지점 1 위도
   * @param lon1 지점 1 경도
   * @param lat2 지점 2 위도
   * @param lon2 지점 2 경도
   * @return Todo: 매장 좌표값은 서울공공데이터API에서 받아온 거 있고 사용자 좌표 받아와서 사용하면 됨 현재 대부분의 브라우저(google chrome)에서는
   *     HTML5 GeoLocation을 사용할 수 없음, 받아오려면 https 환경 구축해야한다고 함 도메인 구해서 certbot에서 어쩌고 하면 개쉽게 된다고 함
   */
  public double distance(double lat1, double lon1, double lat2, double lon2) {

    double theta = lon1 - lon2;
    double dist =
        Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
            + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

    dist = Math.acos(dist);
    dist = rad2deg(dist);
    dist = dist * 60 * 1.1515;

    dist = dist * 1609.344;

    return (dist);
  }
}
