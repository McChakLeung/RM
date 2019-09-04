package com.dgpalife.resourcemanagement.service;

import java.util.List;
import java.util.Map;

public interface ResourceService {

    Map selectAllResource();

    List<Department> selectSubDept(String parentDeptNo);

    Map selectByOption(Map<String, Object> optionMap);
}
