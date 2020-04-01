package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.bean.WorkBean;
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
    public WorkBean getWork(int id) {
        return null;
    }

    @Override
    public List<WorkBean> getCurWorks() {
        return null;
    }

    @Override
    public List<WorkBean> getToDoWorks() {
        return null;
    }
}
