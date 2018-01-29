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

        }

        if (sportPlan.getEndTime() == null) {

        }

        if (sportPlan.getTargetWeight() == null) {

        }
    }
}
