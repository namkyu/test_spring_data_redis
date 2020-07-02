package com.example.nklee.common;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-06-01
 * @Author : nklee
 * @Description :
 */
public class CacheKey {

    private CacheKey() {
    }

    public static final int DEFAULT_EXPIRE_SEC = 60;
    public static final String USER = "user";
    public static final int USER_EXPIRE_SEC = 180;

}
