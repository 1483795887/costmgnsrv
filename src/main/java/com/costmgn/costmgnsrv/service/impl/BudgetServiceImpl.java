package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.entity.Budget;
import com.costmgn.costmgnsrv.entity.User;
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
    public Budget getBudget(int id) {
        return null;
    }

    @Override
    public List<Budget> getBudgets(User user, int type) {
        return null;
    }

    @Override
    public void addBudget(Budget bean) {

    }

    @Override
    public void updateBudget(Budget bean) {

    }
}
