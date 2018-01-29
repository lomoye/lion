package com.lomoye.lion.core.service.impl;

import com.lomoye.lion.core.domain.SportItemLog;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.domain.WeightRecord;
import com.lomoye.lion.core.manager.SportItemLogManager;
import com.lomoye.lion.core.manager.WeightRecordManager;
import com.lomoye.lion.core.service.WeightRecordService;
import com.lomoye.lion.core.validator.WeightRecordValidator;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lomoye on 2018/1/29.
 *
 */
@Service
public class WeightRecordServiceImpl implements WeightRecordService {
    @Autowired
    private WeightRecordManager weightRecordManager;

    @Autowired
    private SportItemLogManager sportItemLogManager;

    @Override
    public WeightRecord addWeightRecord(User user, WeightRecord weightRecord) {
        WeightRecordValidator.ensureAddWeightRecordParam(user.getId(), weightRecord);
        weightRecordManager.save(weightRecord);

        if (CollectionUtils.isEmpty(weightRecord.getSportItemLogList())) {
            return weightRecord;
        }
        for (SportItemLog sportItemLog : weightRecord.getSportItemLogList()) {
            sportItemLog.setDay(weightRecord.getDay());
            sportItemLog.setWeightRecordId(weightRecord.getId());
            sportItemLogManager.save(sportItemLog);
        }

        return weightRecord;
    }
}
