package com.one.eatmotion.config.redis;

public class CacheKey {
    public static final int DEFAULT_EXPIRE_SEC = 60; // 1 minutes
    public static final String SHOP = "shop";
    public static final int SHOP_EXPIRE_SEC = 60 * 5; // 5 minutes
    public static final String FOODS = "foods";
}