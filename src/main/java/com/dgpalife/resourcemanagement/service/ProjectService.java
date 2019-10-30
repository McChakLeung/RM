package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.Project;

import java.util.Map;

public interface ProjectService {
    Page<Object> selectProjectList(Map<String, Object> params);

    int saveProject(Project project);

    Project selectProjectById(Long id);

    int updateProjectById(Project project);

    int deleteProjectById(Long id);
}
