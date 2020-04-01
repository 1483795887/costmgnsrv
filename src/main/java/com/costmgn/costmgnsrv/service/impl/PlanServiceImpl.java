package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.bean.PlanBean;
import com.costmgn.costmgnsrv.bean.UserBean;
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
    public PlanBean getPlan(int id) {
        return null;
    }

    @Override
    public List<PlanBean> getPlans(UserBean user) {
        return null;
    }

    @Override
    public void addPlan(PlanBean bean) {

    }

    @Override
    public void updatePlan(PlanBean bean) {

    }

    @Override
    public void updatePlanStatus(PlanBean bean) {

    }
}
