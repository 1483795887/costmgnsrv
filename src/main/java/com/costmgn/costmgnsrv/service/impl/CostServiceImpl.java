package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.entity.Receipt;
import com.costmgn.costmgnsrv.entity.User;
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
    public Receipt getCost(int id) {
        return null;
    }

    @Override
    public List<Receipt> getCosts(User user, int type) {
        return null;
    }

    @Override
    public void addCost(Receipt bean) {

    }
}
