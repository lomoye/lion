package com.lomoye.lion.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lomoye on 2018/6/10.
 *
 */
public class ReportModel {
    private List<String> columns;

    private List<Map<String, Object>> rows = new ArrayList<>();

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }
}
