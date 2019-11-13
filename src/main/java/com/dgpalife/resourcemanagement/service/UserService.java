package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.model.UserRole;

import java.util.List;
import java.util.Map;

public interface UserService {
    User selectUserByUserNameAndPassword(String loginacct, String password);

    Page<User> selectUserList(Map<String, Object> params);

    Integer saveUser(User user);

    User selectUserByID(Long id);

    Integer updateUser(User user);

    void deleteUserById(Long id);

    void saveUserRoleByBatch(List<UserRole> userRoleList);

    void deleteUserRoleByBatch(List<UserRole> userRoleList);

    User selectUserByLoginAccAndUserPassword(Map<String, Object> params);

    /**
     * 添加用户并[同步其他数据库]
     * <ul>
     * <li>step 1: 保存系统用户，同时设置和部门的关系</li>
     * <li>step 2: 同步用户信息到activiti的identity.User，同时设置角色</li>
     * </ul>
     * @param user              用户对象
     * @param orgId             部门ID
     * @param roleIds           角色ID集合
     * @param synToActiviti     是否同步到Activiti数据库，通过配置文件方式设置，使用属性：account.user.add.syntoactiviti
     * @throws  Exception                       其他未知异常
     */
    void saveToActiviti(User user, Long orgId, List<Long> roleIds, boolean synToActiviti)
            throws Exception;

    /**
     * 删除用户
     * @param userId        用户ID
     * @param synToActiviti     是否同步到Activiti数据库，通过配置文件方式设置，使用属性：account.user.add.syntoactiviti
     * @throws Exception
     */
    void delete(Long userId, boolean synToActiviti) throws Exception;

    /**
     * 同步用户、角色数据到工作流
     * @throws Exception
     */
    void synAllUserAndRoleToActiviti() throws Exception;

    /**
     * 删除工作流引擎Activiti的用户、角色以及关系
     * @throws Exception
     */
    void deleteAllActivitiIdentifyData() throws Exception;
}
