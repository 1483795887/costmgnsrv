package com.costmgn.costmgnsrv.mapper;

import com.costmgn.costmgnsrv.entity.Budget;
import com.costmgn.costmgnsrv.entity.BudgetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BudgetMapper {
    long countByExample(BudgetExample example);

    int deleteByExample(BudgetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Budget record);

    int insertSelective(Budget record);

    List<Budget> selectByExample(BudgetExample example);

    Budget selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Budget record, @Param("example") BudgetExample example);

    int updateByExample(@Param("record") Budget record, @Param("example") BudgetExample example);

    int updateByPrimaryKeySelective(Budget record);

    int updateByPrimaryKey(Budget record);
}