package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.Budget;
import com.costmgn.costmgnsrv.entity.User;

import java.util.List;

public interface BudgetService {
    Budget getBudget(int id);

    List<Budget> getBudgets(User user, int type);

    void addBudget(Budget bean, User user);

    void updateBudget(Budget bean);
}
