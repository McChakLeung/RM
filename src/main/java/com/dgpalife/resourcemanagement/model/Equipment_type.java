package com.dgpalife.resourcemanagement.model;

public class Equipment_type {
    private Long id;

    private String equipmentName;

    private String equipmentType;

    private Integer telphone_portnum;

    private Integer network_portnum;

    private Long creatorId;

    private String createTime;

    public Integer getTelphone_portnum() {
        return telphone_portnum;
    }

    public void setTelphone_portnum(Integer telphone_portnum) {
        this.telphone_portnum = telphone_portnum;
    }

    public Integer getNetwork_portnum() {
        return network_portnum;
    }

    public void setNetwork_portnum(Integer network_portnum) {
        this.network_portnum = network_portnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
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