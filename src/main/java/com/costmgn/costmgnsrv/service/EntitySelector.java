package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.mapper.WorkMapper;
import com.costmgn.costmgnsrv.utils.EntityType;
import com.costmgn.costmgnsrv.utils.Post;
import com.costmgn.costmgnsrv.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntitySelector {
    private WorkMapper mapper;

    @Autowired
    public EntitySelector(WorkMapper mapper) {
        this.mapper = mapper;
    }

    public List<Integer> getIds(User user, int type, int entityType) {
        List<Integer> ids = new ArrayList<>();
        Post post = Post.values()[user.getPost()];
        switch (type) {
            case 1: //维护
                if (post == Post.SalesMan && entityType != EntityType.BUDGET.ordinal()) {
                    ids = mapper.selectByUserId(user.getId(), Status.NOT_SUBMITTED.ordinal(), entityType);
                    ids.addAll(mapper.selectByUserId(user.getId(), Status.REFUSED.ordinal(), entityType));
                } else if (post == Post.DepartmentManager && entityType == EntityType.BUDGET.ordinal()) {
                    ids = mapper.selectByDepartment(user.getDepartment(), Status.NOT_PASSED.ordinal(), entityType);
                    ids.addAll(mapper.selectByDepartment(user.getDepartment(), Status.REFUSED.ordinal(), entityType));
                }
                break;
            case 2://审计
                switch (post) {
                    case DepartmentManager:
                        ids = mapper.selectByDepartment(user.getDepartment()
                                , Status.NOT_AUDITED.ordinal(), entityType);
                        break;
                    case SystemManager:
                        ids = mapper.selectAll(Status.NOT_PASSED.ordinal(), entityType);
                        break;
                }
                break;
            case 3:
                int status = Status.FINISHED.ordinal();
                switch (post) {
                    case SalesMan:
                        ids = mapper.selectByUserId(user.getId(), status, entityType);
                        break;
                    case DepartmentManager:
                        ids = mapper.selectByDepartment(user.getDepartment(), status, entityType);
                        break;
                    case SystemManager:
                        ids = mapper.selectAll(status, entityType);
                        break;
                }
        }
        return ids;
    }
}
