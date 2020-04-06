package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.entity.Plan;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.mapper.PlanMapper;
import com.costmgn.costmgnsrv.mapper.WorkMapper;
import com.costmgn.costmgnsrv.service.PlanService;
import com.costmgn.costmgnsrv.utils.Post;
import com.costmgn.costmgnsrv.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    private PlanMapper mapper;
    private WorkMapper workMapper;

    @Autowired
    public PlanServiceImpl(PlanMapper mapper, WorkMapper workMapper) {
        this.mapper = mapper;
        this.workMapper = workMapper;
    }

    @Override
    public Plan getPlan(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Plan> getPlans(User user, int type) {
        List<Plan> plans = new ArrayList<>();
        Post post = Post.values()[user.getPost()];
        switch (type) {
            case 1: //维护
                if (post == Post.SalesMan) {
                    plans = mapper.selectByUserId(user.getId(), Status.NOT_SUBMITTED.ordinal());
                    plans.addAll(mapper.selectByUserId(user.getId(), Status.REFUSED.ordinal()));
                }
                break;
            case 2://审计
                switch (post) {
                    case DepartmentManager:
                        plans = mapper.selectByDepartment(user.getDepartment()
                                , Status.NOT_AUDITED.ordinal());
                        break;
                    case SystemManager:
                        plans = mapper.selectAll(Status.NOT_PASSED.ordinal());
                        break;
                }
            case 3:
                int status = Status.FINISHED.ordinal();
                switch (post) {
                    case SalesMan:
                        plans = mapper.selectByUserId(user.getId(), status);
                        break;
                    case DepartmentManager:
                        plans = mapper.selectByDepartment(user.getDepartment(), status);
                        break;
                    case SystemManager:
                        plans = mapper.selectAll(status);
                        break;
                }
        }

        return plans;
    }

    @Override
    public void addPlan(Plan bean, User user) {
        Work work = new Work();
        work.setUser(user);
        work.setDepartment(user.getDepartment());
        work.setTitle("方案");
        work.setDate(new Date());
        work.setStatus(Status.NOT_SUBMITTED.ordinal());
        workMapper.insert(work);
        bean.setWork(work);
        mapper.insert(bean);
    }

    @Override
    public void updatePlan(Plan bean) {
        mapper.updateByPrimaryKey(bean);
    }
}
