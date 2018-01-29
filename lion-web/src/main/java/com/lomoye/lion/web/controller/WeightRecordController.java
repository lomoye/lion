package com.lomoye.lion.web.controller;

import com.lomoye.common.dto.ResultData;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.domain.WeightRecord;
import com.lomoye.lion.core.service.WeightRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lomoye on 2018/1/29.
 * 体重记录控制器
 */
@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/api/weightRecord")
public class WeightRecordController extends BaseController {

    @Autowired
    private WeightRecordService weightRecordService;

    /**
     * 添加体重记录
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    ResultData<WeightRecord> addWeightRecord(HttpServletRequest request, @RequestBody WeightRecord weightRecord) {
        User user = getSessionUser(request);
        weightRecordService.addWeightRecord(user, weightRecord);
        return new ResultData<>(weightRecord);
    }
}
