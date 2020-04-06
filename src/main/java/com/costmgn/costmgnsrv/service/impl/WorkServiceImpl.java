package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.entity.WorkExample;
import com.costmgn.costmgnsrv.mapper.WorkMapper;
import com.costmgn.costmgnsrv.service.WorkService;
import com.costmgn.costmgnsrv.utils.Post;
import com.costmgn.costmgnsrv.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {
    private WorkMapper mapper;

    @Autowired
    public WorkServiceImpl(WorkMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Work> getCurWorks(User user) {
        WorkExample workExample = new WorkExample();
        if (user.getPost() != Post.SalesMan.ordinal()
                && user.getPost() != Post.DepartmentManager.ordinal())
            return new ArrayList<>();
        workExample.createCriteria().andUserIdEqualTo(user.getId())
                .andStatusEqualTo(Status.NOT_SUBMITTED.ordinal());
        workExample.or().andUserIdEqualTo(user.getId())
                .andStatusEqualTo(Status.REFUSED.ordinal());
        Post post = Post.values()[user.getPost()];
        switch (post) {
            case SalesMan:
                workExample.or().andUserIdEqualTo(user.getId())
                        .andStatusEqualTo(Status.NOT_AUDITED.ordinal());
                break;
            case DepartmentManager:
                workExample.or().andUserIdEqualTo(user.getId())
                        .andStatusEqualTo(Status.NOT_PASSED.ordinal());
                break;
        }
        return mapper.selectByExample(workExample);
    }

    @Override
    public List<Work> getToDoWorks(User user) {
        if (user.getPost() != Post.DepartmentManager.ordinal()
                && user.getPost() != Post.SystemManager.ordinal())
            return new ArrayList<>();
        WorkExample workExample = new WorkExample();
        Post post = Post.values()[user.getPost()];
        switch (post) {
            case DepartmentManager:
                workExample.createCriteria().andDepartmentEqualTo(user.getDepartment())
                        .andStatusEqualTo(Status.NOT_AUDITED.ordinal());
                break;
            case SystemManager:
                workExample.createCriteria().andStatusEqualTo(Status.NOT_PASSED.ordinal());
                break;
        }
        return mapper.selectByExample(workExample);
    }

    @Override
    public void updateStatus(int id, Integer status) {
        Work work = mapper.selectByPrimaryKey(id);
        work.setStatus(status);
        mapper.updateByPrimaryKey(work);
    }

    @Override
    public void addWork(Work work) {
        work.setDate(new Date());
        work.setDepartment(work.getUser().getDepartment());
        work.setStatus(Status.NOT_SUBMITTED.ordinal());
        mapper.insert(work);
    }
}
