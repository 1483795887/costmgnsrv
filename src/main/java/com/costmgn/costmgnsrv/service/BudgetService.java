package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.bean.BudgetBean;
import com.costmgn.costmgnsrv.bean.UserBean;

import java.util.List;

public interface BudgetService {
    BudgetBean getBudget(int id);

    List<BudgetBean> getBudgets(UserBean user);

    void addBudget(BudgetBean bean);

    void updateBudget(BudgetBean bean);

    void updateBudgetStatus(BudgetBean bean);
}
