package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.bean.ContractBean;
import com.costmgn.costmgnsrv.bean.UserBean;

import java.util.List;

public interface ContractService {
    ContractBean getContract(int id);

    List<ContractBean> getContracts(UserBean user);

    void addContract(ContractBean bean);

    void updateContract(ContractBean bean);

    void updateContractStatus(ContractBean bean);
}
