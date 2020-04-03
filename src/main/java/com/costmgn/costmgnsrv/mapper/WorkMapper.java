package com.costmgn.costmgnsrv.mapper;

import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.entity.WorkExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkMapper {
    long countByExample(WorkExample example);

    int insert(Work record);

    List<Work> selectByExample(WorkExample example);

    Work selectByPrimaryKey(Integer id);

    int updateByExample(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByPrimaryKey(Work record);
}