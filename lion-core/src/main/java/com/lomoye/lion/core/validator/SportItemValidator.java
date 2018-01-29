package com.lomoye.lion.core.validator;

import com.google.common.base.Strings;
import com.lomoye.common.exception.BusinessException;
import com.lomoye.common.util.SerializationUtil;
import com.lomoye.lion.core.constant.ErrorCode;
import com.lomoye.lion.core.domain.SportItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lomoye on 2018/1/29.
 * 运动项目验证器
 */
public class SportItemValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SportItemValidator.class);


    public static void ensureAddSportItemParam(Long userId, SportItem sportItem) {
        LOGGER.info("ensureAddSportItemParam|userId={}|sportItem={}", userId, SerializationUtil.gson2String(sportItem));
        if (Strings.isNullOrEmpty(sportItem.getName())) {
            LOGGER.warn("ensureAddSportItemParam|sportItem.name null|userId={}", userId);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "请输入运动项目的名称");
        }
    }
}
