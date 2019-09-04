package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("resourceService")
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Autowired
    private DepartmentMapper departmentMapper;


    /**
     * 该方法是查询资源台账的列表信息，将资源号码列表，部门结果集等
     * 放入到map集合中，并返回至页面
     *
     *
     * @return  resultMap结果集
     */
    @Override
    public Map selectAllResource() {
        //定义map集合
        Map resultMap = new HashMap();

        //查询资源信息数据
        List<HashMap> resourceList = resourcesMapper.selectAllResource();

        //查询部门信息
        List<HashMap> departmentList = departmentMapper.selectParentDept();

        //将查询的结果集添加到map集合中
        resultMap.put("resourceList",resourceList);
        resultMap.put("departmentList",departmentList);

        return resultMap;
    }

    @Override
    public List<Department> selectSubDept(String parentDeptNo) {
        List<Department> departmentList = departmentMapper.selectSubDept(parentDeptNo);
        return departmentList;
    }

    /**
     * 查询带筛选条件的资源号码
     * @param optionMap
     * @return
     */
    @Override
    public Map selectByOption(Map<String, Object> optionMap) {

        //定义map集合
        Map resultMap = new HashMap();

        List<HashMap> resourceList = resourcesMapper.selectByOption(optionMap);

        //查询部门信息
        List<HashMap> departmentList = departmentMapper.selectParentDept();

        //将查询的结果集添加到map集合中
        resultMap.put("resourceList",resourceList);
        resultMap.put("departmentList",departmentList);

        return resultMap;
    }
}
