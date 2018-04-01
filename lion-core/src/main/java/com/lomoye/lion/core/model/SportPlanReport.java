package com.lomoye.lion.core.model;

/**
 * Created by lomoye on 2018/3/30.
 *
 */
public class SportPlanReport {
    private String key;

    private String name;

    private String valueType;//值类型

    public SportPlanReport(String key, String name, String valueType) {
        this.key = key;
        this.name = name;
        this.valueType = valueType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
}
