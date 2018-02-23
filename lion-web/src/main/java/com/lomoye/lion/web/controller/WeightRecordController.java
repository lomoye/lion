package com.lomoye.lion.web.controller;

import com.lomoye.common.dto.ResultData;
import com.lomoye.common.dto.ResultPagedList;
import com.lomoye.common.util.DateUtil;
import com.lomoye.lion.core.domain.SportItemLog;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.domain.WeightRecord;
import com.lomoye.lion.core.domain.WeightRecordImage;
import com.lomoye.lion.core.manager.SportItemLogManager;
import com.lomoye.lion.core.manager.WeightRecordImageManager;
import com.lomoye.lion.core.manager.WeightRecordManager;
import com.lomoye.lion.core.model.WeightRecordSearchModel;
import com.lomoye.lion.core.service.WeightRecordService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private WeightRecordManager weightRecordManager;

    @Autowired
    private SportItemLogManager sportItemLogManager;

    @Autowired
    private WeightRecordImageManager weightRecordImageManager;

    /**
     * 添加体重记录
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    ResultData<WeightRecord> addWeightRecord(HttpServletRequest request, @RequestBody WeightRecord weightRecord) {
        User user = getSessionUser(request);
        weightRecordService.addWeightRecord(user, weightRecord);
        return new ResultData<>(weightRecord);
    }

    @RequestMapping(value = "/today", method = RequestMethod.GET)
    ResultData<WeightRecord> findTodayWeightRecord(HttpServletRequest request) {
        User user = getSessionUser(request);
        WeightRecord weightRecord = weightRecordManager.findByDay(user.getId(), DateUtil.getDailyStartTime(new Date()));
        return new ResultData<>(weightRecord);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    ResultPagedList<WeightRecord> searchWeightRecord(HttpServletRequest request, @RequestBody WeightRecordSearchModel searchModel) {
        User user = getSessionUser(request);
        List<WeightRecord> weightRecordList = weightRecordManager.search(user.getId(), searchModel);
        if (CollectionUtils.isEmpty(weightRecordList)) {
            return new ResultPagedList<>(weightRecordList, 0L, searchModel);
        }

        Set<Long> recordIds = weightRecordList.stream().map(WeightRecord::getId).collect(Collectors.toSet());
        List<SportItemLog> sportItemLogList = sportItemLogManager.listByRecordIds(user.getId(), recordIds);
        Map<Long, List<SportItemLog>> sportItemLogMap = sportItemLogList.stream().collect(Collectors.groupingBy(SportItemLog::getWeightRecordId));

        List<WeightRecordImage> imageList = weightRecordImageManager.listByRecordIds(user.getId(), recordIds);
        Map<Long, List<WeightRecordImage>> imageMap = imageList.stream().collect(Collectors.groupingBy(WeightRecordImage::getWeightRecordId));

        for (WeightRecord weightRecord : weightRecordList) {
            weightRecord.setSportItemLogList(sportItemLogMap.get(weightRecord.getId()));
            weightRecord.setWeightRecordImageList(imageMap.get(weightRecord.getId()));
        }

        long count = weightRecordManager.searchCount(user.getId(), searchModel);
        return new ResultPagedList<>(weightRecordList, count, searchModel);
    }
}
