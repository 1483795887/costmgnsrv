package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.bean.ContractBean;
import com.costmgn.costmgnsrv.bean.UserBean;
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
    public ContractBean getContract(int id) {
        return null;
    }

    @Override
    public List<ContractBean> getContracts(UserBean user) {
        return null;
    }

    @Override
    public void addContract(ContractBean bean) {

    }

    @Override
    public void updateContract(ContractBean bean) {

    }

    @Override
    public void updateContractStatus(ContractBean bean) {

    }
}
