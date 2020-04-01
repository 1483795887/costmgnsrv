package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;

import java.util.List;

public interface WorkService {
    List<Work> getCurWorks(User user);

    List<Work> getToDoWorks(User user);

    void updateStatus(int id, String status);
}
