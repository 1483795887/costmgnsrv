package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.bean.UserBean;
import com.costmgn.costmgnsrv.bean.WorkBean;

import java.util.List;

public interface WorkService {
    WorkBean getWork(int id);

    List<WorkBean> getCurWorks(UserBean userBean);

    List<WorkBean> getToDoWorks(UserBean userBean);
}
