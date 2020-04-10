package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.entity.Contract;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.mapper.ContractMapper;
import com.costmgn.costmgnsrv.mapper.WorkMapper;
import com.costmgn.costmgnsrv.service.ContractService;
import com.costmgn.costmgnsrv.service.EntitySelector;
import com.costmgn.costmgnsrv.utils.EntityType;
import com.costmgn.costmgnsrv.utils.WorkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    private ContractMapper mapper;
    private WorkMapper workMapper;
    private EntitySelector selector;

    @Autowired
    public ContractServiceImpl(ContractMapper mapper, WorkMapper workMapper, EntitySelector selector) {
        this.mapper = mapper;
        this.workMapper = workMapper;
        this.selector = selector;
    }

    @Override
    public Contract getContract(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Contract> getContracts(User user, int type) {
        List<Integer> ids = selector.getIds(user, type, EntityType.CONTRACT.ordinal());
        if (ids.size() == 0) {
            return new ArrayList<>();
        } else {
            return mapper.selectByIds(ids);
        }
    }

    @Override
    public void addContract(Contract bean, User user) {
        Work work = WorkFactory.produceWork(user, EntityType.CONTRACT);
        workMapper.insert(work);
        bean.setWork(work);
        mapper.insert(bean);
        work.setEntityId(bean.getId());
        workMapper.updateByPrimaryKey(work);
    }

    @Override
    public void updateContract(Contract bean) {
        mapper.updateByPrimaryKey(bean);
    }
}
