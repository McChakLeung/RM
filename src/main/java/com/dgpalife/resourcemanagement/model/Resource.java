package com.dgpalife.resourcemanagement.model;

public class Resource {
    private Long id;

    private String resourceNo;

    private String resourcType;

    private String operator;

    private Long networkRoomId;

    private Long departmentId;

    private Long userId;

    private Long equipmentPortId;

    private Long expenseId;

    private String createTime;

    private Long creatorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceNo() {
        return resourceNo;
    }

    public void setResourceNo(String resourceNo) {
        this.resourceNo = resourceNo;
    }

    public String getResourcType() {
        return resourcType;
    }

    public void setResourcType(String resourcType) {
        this.resourcType = resourcType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Long getNetworkRoomId() {
        return networkRoomId;
    }

    public void setNetworkRoomId(Long networkRoomId) {
        this.networkRoomId = networkRoomId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEquipmentPortId() {
        return equipmentPortId;
    }

    public void setEquipmentPortId(Long equipmentPortId) {
        this.equipmentPortId = equipmentPortId;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
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