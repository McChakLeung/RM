package com.dgpalife.resourcemanagement.model;

public class Equipment {
    private Long id;

    private String equipmentSn;

    private String equipmentType;

    private String account_center;

    private String createTime;

    private Long creatorId;

    public String getAccount_center() {
        return account_center;
    }

    public void setAccount_center(String account_center) {
        this.account_center = account_center;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentSn() {
        return equipmentSn;
    }

    public void setEquipmentSn(String equipmentSn) {
        this.equipmentSn = equipmentSn;
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