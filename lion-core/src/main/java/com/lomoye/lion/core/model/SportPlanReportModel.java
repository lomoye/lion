package com.lomoye.lion.core.model;

import com.lomoye.lion.core.domain.SportPlan;

import java.util.List;
import java.util.Map;

/**
 * Created by lomoye on 2018/3/30.
 *
 */
public class SportPlanReportModel {
    private SportPlan sportPlan;

    private List<SportPlanReport> keyInfos;

    private List<Map<String/*key*/, Object>> reports;


    public SportPlan getSportPlan() {
        return sportPlan;
    }

    public void setSportPlan(SportPlan sportPlan) {
        this.sportPlan = sportPlan;
    }

    public List<SportPlanReport> getKeyInfos() {
        return keyInfos;
    }

    public void setKeyInfos(List<SportPlanReport> keyInfos) {
        this.keyInfos = keyInfos;
    }

    public List<Map<String, Object>> getReports() {
        return reports;
    }

    public void setReports(List<Map<String, Object>> reports) {
        this.reports = reports;
    }
}
