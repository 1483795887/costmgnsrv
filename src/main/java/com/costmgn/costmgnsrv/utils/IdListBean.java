package com.costmgn.costmgnsrv.utils;

import java.util.ArrayList;
import java.util.List;

public class IdListBean {
    private List<Integer> idList;

    public IdListBean() {
        idList = new ArrayList<>();
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }
}
