package com.one.eatmotion.schedulers;

import com.one.eatmotion.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

  private final ShopService shopService;

  @Scheduled(fixedDelay = 600 * 1000L, initialDelay = 600 * 1000L)
  public void executeUpdateShopGrade() {
    for (int i = 0; i < shopService.countShop(); i++) {
      shopService.updateShopGrade((long) i);
    }
  }
}
