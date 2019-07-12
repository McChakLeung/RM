package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByUserNameAndPassword(@Param("loginacc") String loginacc, @Param("password") String password);

    void updateLastLoginTime(User user);
}