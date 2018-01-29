package com.lomoye.lion.core.service;

import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.domain.WeightRecord;

/**
 * Created by lomoye on 2018/1/29.
 * 体重记录服务
 */
public interface WeightRecordService {
    WeightRecord addWeightRecord(User user, WeightRecord weightRecord);
}
