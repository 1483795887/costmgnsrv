package com.costmgn.costmgnsrv.utils;

import java.util.ArrayList;
import java.util.List;

public class IdListBean {
    private List<Integer> idList;
    private Integer id;

    public IdListBean() {
        idList = new ArrayList<>();
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
