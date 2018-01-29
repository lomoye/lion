package com.lomoye.lion.core.validator;

import com.lomoye.common.exception.BusinessException;
import com.lomoye.common.util.SerializationUtil;
import com.lomoye.lion.core.constant.ErrorCode;
import com.lomoye.lion.core.domain.SportItemLog;
import com.lomoye.lion.core.domain.WeightRecord;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by lomoye on 2018/1/29.
 * 体重记录验证器
 */
public class WeightRecordValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeightRecordValidator.class);


    public static void ensureAddWeightRecordParam(Long userId, WeightRecord weightRecord) {
        LOGGER.info("ensureAddWeightRecordParam|userId={}|weightRecord={}", userId, SerializationUtil.gson2String(weightRecord));
        if (weightRecord.getDay() == null) {
            LOGGER.warn("ensureAddSportItemParam|day null|userId={}", userId);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "请输入体重记录的日期");
        }
        if (weightRecord.getWeight() == null) {
            LOGGER.warn("ensureAddSportItemParam|weight null|userId={}", userId);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "请输入体重数值");
        }
        if (weightRecord.getIsBreakfast() == null) {
            LOGGER.warn("ensureAddSportItemParam|isBreakfast null|userId={}", userId);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "请输入是否吃过早餐");
        }
        if (weightRecord.getIsLunch() == null) {
            LOGGER.warn("ensureAddSportItemParam|isLunch null|userId={}", userId);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "请输入是否吃过午餐");
        }
        if (weightRecord.getIsDinner() == null) {
            LOGGER.warn("ensureAddSportItemParam|isDinner null|userId={}", userId);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "请输入是否吃过晚餐");
        }
        if (weightRecord.getIsSport() == null) {
            LOGGER.warn("ensureAddSportItemParam|isSport null|userId={}", userId);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "请输入是否运动过");
        }

        checkSportItemLog(userId, weightRecord.getSportItemLogList());
    }

    private static void checkSportItemLog(Long userId, List<SportItemLog> sportItemLogList) {
        if (CollectionUtils.isEmpty(sportItemLogList)) {
            return;
        }
        for (SportItemLog sportItemLog : sportItemLogList) {
            if (sportItemLog.getSportItemId() == null) {
                LOGGER.warn("checkSportItemLog|sportItemId null|userId={}", userId);
                throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "运动项目id不能为空");
            }
            if (sportItemLog.getSportPlanId() == null) {
                LOGGER.warn("checkSportItemLog|sportPlanId null|userId={}", userId);
                throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "运动计划id不能为空");
            }
        }
    }
}
