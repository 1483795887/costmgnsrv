package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.Receipt;
import com.costmgn.costmgnsrv.entity.User;

import java.util.List;

public interface CostService {
    Receipt getCost(int id);

    List<Receipt> getCosts(User user, int type);

    List<Receipt> getCosts(Integer budgetId);

    void addCost(Receipt bean, User user);

    void updateCost(Receipt bean);
}
