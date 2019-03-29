package com.taotao.common.vo;

import java.util.List;

public class EasyUIDateGrideResult {
    private long total;
    private List<?>rows;//用T和用?是不一样的,用?和不写一样

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
