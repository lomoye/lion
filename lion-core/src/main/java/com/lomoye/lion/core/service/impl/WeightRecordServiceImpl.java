package com.lomoye.lion.core.service.impl;

import com.lomoye.common.exception.BusinessException;
import com.lomoye.common.util.DateUtil;
import com.lomoye.lion.core.constant.ErrorCode;
import com.lomoye.lion.core.domain.SportItemLog;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.domain.WeightRecord;
import com.lomoye.lion.core.manager.SportItemLogManager;
import com.lomoye.lion.core.manager.WeightRecordManager;
import com.lomoye.lion.core.service.WeightRecordService;
import com.lomoye.lion.core.validator.WeightRecordValidator;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by lomoye on 2018/1/29.
 *
 */
@Service
public class WeightRecordServiceImpl implements WeightRecordService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeightRecordServiceImpl.class);

    @Autowired
    private WeightRecordManager weightRecordManager;

    @Autowired
    private SportItemLogManager sportItemLogManager;

    @Override
    public WeightRecord addWeightRecord(User user, WeightRecord weightRecord) {
        WeightRecordValidator.ensureAddWeightRecordParam(user.getId(), weightRecord);
        checkHasTodayRecord(user.getId());

        weightRecord.setUserId(user.getId());
        weightRecord.setDay(DateUtil.getDailyStartTime(weightRecord.getDay()));
        weightRecordManager.save(weightRecord);

        if (CollectionUtils.isEmpty(weightRecord.getSportItemLogList())) {
            return weightRecord;
        }
        for (SportItemLog sportItemLog : weightRecord.getSportItemLogList()) {
            sportItemLog.setDay(weightRecord.getDay());
            sportItemLog.setUserId(user.getId());
            sportItemLog.setWeightRecordId(weightRecord.getId());
            sportItemLogManager.save(sportItemLog);
        }

        return weightRecord;
    }

    private void checkHasTodayRecord(Long userId) {
        WeightRecord weightRecord = weightRecordManager.findByDay(userId, DateUtil.getDailyStartTime(new Date()));
        if (weightRecord != null) {
            LOGGER.info("checkHasTodayRecord has today record|userId={}", userId);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "今天的体重记录已存在");
        }
    }
}
