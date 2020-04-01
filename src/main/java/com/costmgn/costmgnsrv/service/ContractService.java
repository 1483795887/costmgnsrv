package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.Contract;
import com.costmgn.costmgnsrv.entity.User;

import java.util.List;

public interface ContractService {
    Contract getContract(int id);

    List<Contract> getContracts(User user, int type);

    void addContract(Contract bean);

    void updateContract(Contract bean);
}
