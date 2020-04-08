package com.costmgn.costmgnsrv.mapper;

import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.entity.WorkExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkMapper {
    long countByExample(WorkExample example);

    int insert(Work record);

    List<Work> selectByExample(WorkExample example);

    List<Integer> selectByUserId(@Param("userid") Integer userid,
                                 @Param("status") Integer status,
                                 @Param("type") Integer type);

    List<Integer> selectByDepartment(@Param("department") Integer departmen,
                                     @Param("status") Integer status,
                                     @Param("type") Integer type);

    List<Integer> selectAll(@Param("status") Integer status,
                            @Param("type") Integer type);

    Work selectByPrimaryKey(Integer id);

    int updateByExample(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByPrimaryKey(Work record);
}