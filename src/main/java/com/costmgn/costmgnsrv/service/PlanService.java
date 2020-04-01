package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.bean.PlanBean;
import com.costmgn.costmgnsrv.bean.UserBean;

import java.util.List;

public interface PlanService {
    PlanBean getPlan(int id);

    List<PlanBean> getPlans(UserBean user);

    void addPlan(PlanBean bean);

    void updatePlan(PlanBean bean);

    void updatePlanStatus(PlanBean bean);
}
