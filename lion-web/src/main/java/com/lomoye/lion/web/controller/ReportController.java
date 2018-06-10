package com.lomoye.lion.web.controller;

import com.google.common.collect.Lists;
import com.lomoye.common.dto.ResultData;
import com.lomoye.common.util.DateUtil;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.domain.WeightRecord;
import com.lomoye.lion.core.manager.WeightRecordManager;
import com.lomoye.lion.core.model.ReportModel;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lomoye on 2018/6/10.
 *
 */
@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/api/report")
public class ReportController extends BaseController {

    @Autowired
    private WeightRecordManager weightRecordManager;

    @RequestMapping(value = "", method = RequestMethod.GET)
    ResultData<ReportModel> getReport(HttpServletRequest request) {
        User user = getSessionUser(request);
        List<WeightRecord> records = weightRecordManager.findAllByUserId(user.getId());

        if (CollectionUtils.isEmpty(records)) {
            return new ResultData<>();
        }

        ReportModel reportModel = new ReportModel();
        reportModel.setColumns(Lists.newArrayList("日期", "体重"));
        records.forEach(record -> {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("日期", DateUtil.format(record.getDay(), "yyyy-MM-dd"));
            map.put("体重", new BigDecimal(record.getWeight()).multiply(new BigDecimal(0.01)).setScale(1, RoundingMode.HALF_UP));
            reportModel.getRows().add(map);
        });
        return new ResultData<>(reportModel);
    }
}
