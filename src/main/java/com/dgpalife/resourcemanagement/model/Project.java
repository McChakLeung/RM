package com.dgpalife.resourcemanagement.model;

import java.util.List;
import java.util.Map;

public class Project {
    private Long id;

    private String projectName;

    private String projectType;

    private String startTime;

    private String createTime;

    private Long creatorId;

    private List<Map<String,Object>> orderList;

    public List<Map<String, Object>> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Map<String, Object>> orderList) {
        this.orderList = orderList;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}