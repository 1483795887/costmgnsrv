package com.costmgn.costmgnsrv.utils;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;

import java.util.Date;

public class WorkFactory {
    public static Work produceWork(User user, EntityType type) {
        Work work = new Work();
        work.setUser(user);
        work.setDepartment(user.getDepartment());
        work.setType(type.ordinal());
        work.setDate(new Date());
        work.setStatus(Status.NOT_SUBMITTED.ordinal());
        return work;
    }
}
