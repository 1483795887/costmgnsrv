package com.costmgn.costmgnsrv.mapper;

import com.costmgn.costmgnsrv.entity.Contract;
import com.costmgn.costmgnsrv.entity.ContractExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractMapper {
    long countByExample(ContractExample example);

    int insert(Contract record);

    List<Contract> selectByExample(ContractExample example);

    List<Contract> selectByIds(@Param("ids") List<Integer> ids);

    Contract selectByPrimaryKey(Integer id);

    int updateByExample(@Param("record") Contract record, @Param("example") ContractExample example);

    int updateByPrimaryKey(Contract record);
}