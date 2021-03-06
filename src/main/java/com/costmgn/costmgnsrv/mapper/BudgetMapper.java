package com.costmgn.costmgnsrv.mapper;

import com.costmgn.costmgnsrv.entity.Budget;
import com.costmgn.costmgnsrv.entity.BudgetExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetMapper {
    long countByExample(BudgetExample example);

    int insert(Budget record);

    List<Budget> selectByExample(BudgetExample example);

    List<Budget> selectByIds(@Param("ids") List<Integer> ids);

    Budget selectByPrimaryKey(Integer id);

    int updateByExample(@Param("record") Budget record, @Param("example") BudgetExample example);

    int updateByPrimaryKey(Budget record);
}