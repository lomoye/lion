package com.lomoye.lion.core.domain;

import com.lomoye.common.domain.CommonDomain;

/**
 * Created by lomoye on 2018/1/26.
 * 体重记录里上传的图片
 */
public class WeightRecordImage extends CommonDomain {
    private Long userId;

    private Long weightRecordId;

    private String imageUrl;//图片地址

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWeightRecordId() {
        return weightRecordId;
    }

    public void setWeightRecordId(Long weightRecordId) {
        this.weightRecordId = weightRecordId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
