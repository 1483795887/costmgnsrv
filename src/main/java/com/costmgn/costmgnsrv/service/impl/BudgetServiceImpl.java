package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.bean.BudgetBean;
import com.costmgn.costmgnsrv.bean.UserBean;
import com.costmgn.costmgnsrv.mapper.BudgetMapper;
import com.costmgn.costmgnsrv.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {
    private BudgetMapper mapper;

    @Autowired
    public BudgetServiceImpl(BudgetMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BudgetBean getBudget(int id) {
        return null;
    }

    @Override
    public List<BudgetBean> getBudgets(UserBean user) {
        return null;
    }

    @Override
    public void addBudget(BudgetBean bean) {

    }

    @Override
    public void updateBudget(BudgetBean bean) {

    }

    @Override
    public void updateBudgetStatus(BudgetBean bean) {

    }
}
