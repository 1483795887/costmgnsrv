package com.costmgn.costmgnsrv.utils;

import java.util.List;

public class OccupyBean {
    List<Integer> receiptIdList;
    Integer budgetId;

    public List<Integer> getReceiptIdList() {
        return receiptIdList;
    }

    public void setReceiptIdList(List<Integer> receiptIdList) {
        this.receiptIdList = receiptIdList;
    }

    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }
}
