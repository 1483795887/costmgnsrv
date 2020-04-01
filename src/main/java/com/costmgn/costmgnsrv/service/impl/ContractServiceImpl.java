package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.entity.Contract;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.mapper.ContractMapper;
import com.costmgn.costmgnsrv.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    private ContractMapper mapper;

    @Autowired
    public ContractServiceImpl(ContractMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public Contract getContract(int id) {
        return null;
    }

    @Override
    public List<Contract> getContracts(User user, int type) {
        return null;
    }

    @Override
    public void addContract(Contract bean) {

    }

    @Override
    public void updateContract(Contract bean) {

    }
}
