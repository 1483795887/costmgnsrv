package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.mapper.WorkMapper;
import com.costmgn.costmgnsrv.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public List<Work> getToDoWorks(User user) {
        return null;
    }

    @Override
    public void updateStatus(int id, String status) {

    }
}
