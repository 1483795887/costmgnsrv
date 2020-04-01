package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.Receipt;
import com.costmgn.costmgnsrv.entity.User;

import java.util.List;

public interface CostService {
    Receipt getCost(int id);

    List<Receipt> getCosts(User user, int type);

    void addCost(Receipt bean);
}
