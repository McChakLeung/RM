package com.dgpalife.resourcemanagement.model;

public class EquipmentPurchaseRecord {
    private Long id;

    private Integer purchaseNum;

    private Double expenseId;

    private String accountCenter;

    private String equipmentType;

    private Long projectId;

    private Long orderId;

    private Long creatorId;

    private String createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public Double getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Double expenseId) {
        this.expenseId = expenseId;
    }

    public String getAccountCenter() {
        return accountCenter;
    }

    public void setAccountCenter(String accountCenter) {
        this.accountCenter = accountCenter;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}