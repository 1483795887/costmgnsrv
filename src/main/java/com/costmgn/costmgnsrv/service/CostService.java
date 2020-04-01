package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.bean.CostBean;
import com.costmgn.costmgnsrv.bean.UserBean;

import java.util.List;

public interface CostService {
    CostBean getCost(int id);

    List<CostBean> getCosts(UserBean user);

    void addCost(CostBean bean);

    void updateCostStatus(CostBean bean);
}
