package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.entity.Plan;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.mapper.PlanMapper;
import com.costmgn.costmgnsrv.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    private PlanMapper mapper;

    @Autowired
    public PlanServiceImpl(PlanMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Plan getPlan(int id) {
        return null;
    }

    @Override
    public List<Plan> getPlans(User user, int type) {
        return null;
    }

    @Override
    public void addPlan(Plan bean) {

    }

    @Override
    public void updatePlan(Plan bean) {

    }
}
