package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.RoleMapper;
import com.dgpalife.resourcemanagement.mapper.RolePermissionMapper;
import com.dgpalife.resourcemanagement.model.Permission;
import com.dgpalife.resourcemanagement.model.Role;
import com.dgpalife.resourcemanagement.model.RolePermission;
import com.dgpalife.resourcemanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Role> queryAllRole() {
        return roleMapper.queryAllRole();
    }

    @Override
    public Page<Role> queryRoleListByParams(Map<String, Object> params) {
        //初始化Page对象
        Page<Role> page = new Page((Integer) params.get("pageno"),(Integer)params.get("pagesize"));
        //根据初始化Page类对象获得起始行的值
        Integer startline = page.getStartline();
        //将startline存入到参数集合中
        params.put("startline",startline);
        //将数据库中查询出来的Role数据查询出来并存放到page对象中，将page对象返回给控制器
        List<Role> roleList = roleMapper.queryRoleListByParams(params);
        page.setDatalist(roleList);
        return page;
    }

    @Override
    public List<Permission> queryPermissionByRoleID(Integer roleId) {
        return roleMapper.queryPermissionByRoleID(roleId);
    }

    @Override
    public Integer processAssignPermission(Long roleId, List<Long> ids) {
        //删除roleId对应的权限
        roleMapper.deletePermissionByRoleID(roleId);

        int totalCount = 0 ;

        for (Long permissionid : ids) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionid);
            int count = rolePermissionMapper.insertSelective(rolePermission);
            totalCount += count ;
        }

        return totalCount;
    }

    @Override
    public Integer saveRole(Role role) {
        return roleMapper.insertSelective(role);
    }

    @Override
    public Role queryRoleByRoleId(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public Integer updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void deleteRoleByKey(Long roleId) {
        roleMapper.deleteByPrimaryKey(roleId);
        rolePermissionMapper.deleteByRoleId(roleId);
    }

    @Override
    public List queryRoleInfo(Long id) {
        return roleMapper.queryRoleInfo(id);
    }
}
