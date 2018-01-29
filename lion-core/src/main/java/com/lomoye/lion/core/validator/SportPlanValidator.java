package com.lomoye.lion.core.validator;

import com.lomoye.common.exception.BusinessException;
import com.lomoye.common.util.SerializationUtil;
import com.lomoye.lion.core.constant.ErrorCode;
import com.lomoye.lion.core.domain.SportPlan;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lomoye on 2018/1/29.
 * 运动计划验证器
 */
public class SportPlanValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SportPlanValidator.class);

    public static void ensureAddSportPlanParam(Long userId, SportPlan sportPlan) {
        LOGGER.info("ensureAddSportPlanParam|userId={}|sportItem={}", userId, SerializationUtil.gson2String(sportPlan));
        if (CollectionUtils.isEmpty(sportPlan.getSportItemList())) {
            LOGGER.warn("ensureAddSportPlanParam|sportItemList empty|userId={}", userId);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "请至少选择一个运动项目");
        }
        if (sportPlan.getStartTime() == null) {
            LOGGER.warn("ensureAddSportPlanParam|startTime null|userId={}", userId);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "请选择开始时间");
        }

        if (sportPlan.getEndTime() == null) {
            LOGGER.warn("ensureAddSportPlanParam|endTime null|userId={}", userId);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "请选择结束时间");
        }

        if (sportPlan.getTargetWeight() == null) {
            LOGGER.warn("ensureAddSportPlanParam|targetWeight null|userId={}", userId);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "请选择目标体重");
        }
    }
}
