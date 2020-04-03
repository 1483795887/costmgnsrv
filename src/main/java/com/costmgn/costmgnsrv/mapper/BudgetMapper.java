package com.costmgn.costmgnsrv.mapper;

import com.costmgn.costmgnsrv.entity.Budget;
import com.costmgn.costmgnsrv.entity.BudgetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BudgetMapper {
    long countByExample(BudgetExample example);

    int insert(Budget record);

    List<Budget> selectByExample(BudgetExample example);

    Budget selectByPrimaryKey(Integer id);

    int updateByExample(@Param("record") Budget record, @Param("example") BudgetExample example);

    int updateByPrimaryKey(Budget record);
}