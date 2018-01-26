package com.lomoye.lion.core.constant;

/**
 * Created by lomoye on 2017/7/1.
 *
 */
public class SessionConstant {
    public static final String USER = "user";//当前用户

    public static final Long LIMIT_RETRY_LOGIN = 5L;//登录连续失败最多次数

    public static final Long LIMIT_RETRY_LOGIN_TIME = 30 * 1000 * 60L;//登录连续失败最多次后锁定的时间 毫秒
}
