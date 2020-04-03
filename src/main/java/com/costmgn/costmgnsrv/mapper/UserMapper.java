package com.costmgn.costmgnsrv.mapper;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int insert(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKey(User record);

    int selectMaxId();
}