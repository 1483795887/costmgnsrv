package com.costmgn.costmgnsrv.mapper;

import com.costmgn.costmgnsrv.entity.Plan;
import com.costmgn.costmgnsrv.entity.PlanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlanMapper {
    long countByExample(PlanExample example);

    int insert(Plan record);

    List<Plan> selectByExample(PlanExample example);

    Plan selectByPrimaryKey(Integer id);

    List<Plan> selectByUserId(@Param("userid") Integer userid, @Param("status") Integer status);

    List<Plan> selectByDepartment(@Param("department") Integer department, @Param("status") Integer status);

    List<Plan> selectAll(@Param("status") Integer status);

    int updateByExample(@Param("record") Plan record, @Param("example") PlanExample example);

    int updateByPrimaryKey(Plan record);
}