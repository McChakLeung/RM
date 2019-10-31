package com.dgpalife.resourcemanagement.model;

import java.util.List;

public class Workplace {
    private Long id;

    private String workplaceName;

    private String address;

    private String district;

    private Long creatorId;

    private String createTime;

    private List<NetworkRoom> networkRoomList;

    public String getWorkplaceName() {
        return workplaceName;
    }

    public void setWorkplaceName(String workplaceName) {
        this.workplaceName = workplaceName;
    }

    public List<NetworkRoom> getNetworkRoomList() {
        return networkRoomList;
    }

    public void setNetworkRoomList(List<NetworkRoom> networkRoomList) {
        this.networkRoomList = networkRoomList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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