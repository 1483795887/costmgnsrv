package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.Plan;
import com.costmgn.costmgnsrv.entity.User;

import java.util.List;

public interface PlanService {
    Plan getPlan(int id);

    List<Plan> getPlans(User user, int type);

    void addPlan(Plan bean, User user);

    void updatePlan(Plan bean);
}
