package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.entity.Budget;
import com.costmgn.costmgnsrv.entity.Receipt;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.mapper.BudgetMapper;
import com.costmgn.costmgnsrv.mapper.ReceiptMapper;
import com.costmgn.costmgnsrv.mapper.WorkMapper;
import com.costmgn.costmgnsrv.service.CostService;
import com.costmgn.costmgnsrv.service.EntitySelector;
import com.costmgn.costmgnsrv.utils.EntityType;
import com.costmgn.costmgnsrv.utils.WorkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostServiceImpl implements CostService {
    private ReceiptMapper mapper;
    private WorkMapper workMapper;
    private BudgetMapper budgetMapper;
    private EntitySelector selector;

    @Autowired
    public CostServiceImpl(ReceiptMapper mapper,
                           WorkMapper workMapper,
                           BudgetMapper budgetMapper,
                           EntitySelector selector) {
        this.mapper = mapper;
        this.workMapper = workMapper;
        this.budgetMapper = budgetMapper;
        this.selector = selector;
    }

    @Override
    public Receipt getCost(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Receipt> getCosts(User user, int type) {
        return mapper.selectByIds(selector.getIds(user, type, EntityType.RECEIPT.ordinal()));
    }

    @Override
    public List<Receipt> getCosts(Integer budgetId) {
        return mapper.selectByBudgetIds(budgetId);
    }

    @Override
    public void addCost(Receipt bean, User user) {
        Work work = WorkFactory.produceWork(user, EntityType.RECEIPT);
        workMapper.insert(work);
        bean.setWork(work);
        mapper.insert(bean);
        work.setEntityId(bean.getId());
        workMapper.updateByPrimaryKey(work);

        Budget budget = budgetMapper.selectByPrimaryKey(bean.getBudgetId());
        budget.setOccupyMoney(budget.getOccupyMoney().add(bean.getMoney()));
        budgetMapper.updateByPrimaryKey(budget);
    }

    @Override
    public void updateCost(Receipt bean) {
        mapper.updateByPrimaryKey(bean);
    }
}
