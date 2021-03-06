package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.entity.Plan;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.mapper.PlanMapper;
import com.costmgn.costmgnsrv.mapper.WorkMapper;
import com.costmgn.costmgnsrv.service.EntitySelector;
import com.costmgn.costmgnsrv.service.PlanService;
import com.costmgn.costmgnsrv.utils.EntityType;
import com.costmgn.costmgnsrv.utils.WorkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    private PlanMapper mapper;
    private WorkMapper workMapper;
    private EntitySelector selector;

    @Autowired
    public PlanServiceImpl(PlanMapper mapper, WorkMapper workMapper, EntitySelector selector) {
        this.mapper = mapper;
        this.workMapper = workMapper;
        this.selector = selector;
    }

    @Override
    public Plan getPlan(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Plan> getPlans(User user, int type) {
        List<Integer> ids = selector.getIds(user, type, EntityType.PLAN.ordinal());
        if (ids.size() == 0) {
            return new ArrayList<>();
        } else {
            return mapper.selectByIds(ids);
        }
    }

    @Override
    public void addPlan(Plan bean, User user) {
        Work work = WorkFactory.produceWork(user, EntityType.PLAN);
        workMapper.insert(work);
        bean.setWork(work);
        mapper.insert(bean);
        work.setEntityId(bean.getId());
        workMapper.updateByPrimaryKey(work);
    }

    @Override
    public void updatePlan(Plan bean) {
        mapper.updateByPrimaryKey(bean);
    }
}
