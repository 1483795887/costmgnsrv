package com.costmgn.costmgnsrv.mapper;

import com.costmgn.costmgnsrv.entity.Receipt;
import com.costmgn.costmgnsrv.entity.ReceiptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReceiptMapper {
    long countByExample(ReceiptExample example);

    int insert(Receipt record);

    List<Receipt> selectByExample(ReceiptExample example);

    List<Receipt> selectByIds(@Param("ids") List<Integer> ids);

    Receipt selectByPrimaryKey(Integer id);

    int updateByExample(@Param("record") Receipt record, @Param("example") ReceiptExample example);

    int updateByPrimaryKey(Receipt record);
}