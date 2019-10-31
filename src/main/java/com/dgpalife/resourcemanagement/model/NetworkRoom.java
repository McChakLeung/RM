package com.dgpalife.resourcemanagement.model;

public class NetworkRoom {
    private Long id;

    private String networkroom_name;

    private String floor;

    private Long workplaceId;

    private String createTime;

    private Long creatorId;

    private Workplace workplace;

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNetworkroom_name() {
        return networkroom_name;
    }

    public void setNetworkroom_name(String networkroom_name) {
        this.networkroom_name = networkroom_name;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Long getWorkplaceId() {
        return workplaceId;
    }

    public void setWorkplaceId(Long workplaceId) {
        this.workplaceId = workplaceId;
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