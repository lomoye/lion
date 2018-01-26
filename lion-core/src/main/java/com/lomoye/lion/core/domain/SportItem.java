package com.lomoye.lion.core.domain;

import com.lomoye.common.domain.CommonDomain;

/**
 * Created by lomoye on 2018/1/26.
 * 系统内置的运动项目
 */
public class SportItem extends CommonDomain {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
