package com.lomoye.lion.core.domain;

import com.lomoye.common.domain.CommonDomain;

/**
 * Created by lomoye on 2018/1/26.
 * 用户自己建立的运动项目
 */
public class SportItem extends CommonDomain {
    private Long userId;

    private String name;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
