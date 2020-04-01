package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.bean.WorkBean;

import java.util.List;

public interface WorkService {
    WorkBean getWork(int id);

    List<WorkBean> getCurWorks();

    List<WorkBean> getToDoWorks();
}
