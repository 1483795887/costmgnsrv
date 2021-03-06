package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.entity.Budget;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.mapper.BudgetMapper;
import com.costmgn.costmgnsrv.mapper.WorkMapper;
import com.costmgn.costmgnsrv.service.BudgetService;
import com.costmgn.costmgnsrv.service.EntitySelector;
import com.costmgn.costmgnsrv.utils.EntityType;
import com.costmgn.costmgnsrv.utils.WorkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {
    private BudgetMapper mapper;
    private WorkMapper workMapper;
    private EntitySelector selector;

    @Autowired
    public BudgetServiceImpl(BudgetMapper mapper, WorkMapper workMapper, EntitySelector selector) {
        this.mapper = mapper;
        this.workMapper = workMapper;
        this.selector = selector;
    }

    @Override
    public Budget getBudget(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Budget> getBudgets(User user, int type) {
        List<Integer> ids = selector.getIds(user, type, EntityType.BUDGET.ordinal());
        if (ids.size() == 0) {
            return new ArrayList<>();
        } else {
            return mapper.selectByIds(ids);
        }
    }

    @Override
    public void addBudget(Budget bean, User user) {
        Work work = WorkFactory.produceWork(user, EntityType.BUDGET);
        workMapper.insert(work);
        bean.setWork(work);
        bean.setOccupyMoney(new BigDecimal(0));
        mapper.insert(bean);
        work.setEntityId(bean.getId());
        workMapper.updateByPrimaryKey(work);
    }

    @Override
    public void updateBudget(Budget bean) {
        mapper.updateByPrimaryKey(bean);
    }
}
