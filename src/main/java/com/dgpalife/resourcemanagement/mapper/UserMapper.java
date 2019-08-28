package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByUserNameAndPassword(@Param("loginacct") String loginacct, @Param("password") String password);

    void updateLastLoginTime(User user);

    List<User> selectUserList(Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);
}