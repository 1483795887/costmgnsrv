package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.bean.CostBean;
import com.costmgn.costmgnsrv.bean.UserBean;
import com.costmgn.costmgnsrv.mapper.ReceiptMapper;
import com.costmgn.costmgnsrv.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostServiceImpl implements CostService {
    private ReceiptMapper mapper;

    @Autowired
    public CostServiceImpl(ReceiptMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public CostBean getCost(int id) {
        return null;
    }

    @Override
    public List<CostBean> getCosts(UserBean user) {
        return null;
    }

    @Override
    public void addCost(CostBean bean) {

    }

    @Override
    public void updateCostStatus(CostBean bean) {

    }
}
