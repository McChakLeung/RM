package com.dgpalife.resourcemanagement.model;

public class ChangeItemRecord {
    private Long id;

    private Long resourceId;

    private Integer changeItemId;

    private Long beforeChange;

    private Long afterChange;

    private Long orderId;

    private String createtime;

    private Long creatorId;

    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getChangeItemId() {
        return changeItemId;
    }

    public void setChangeItemId(Integer changeItemId) {
        this.changeItemId = changeItemId;
    }

    public Long getBeforeChange() {
        return beforeChange;
    }

    public void setBeforeChange(Long beforeChange) {
        this.beforeChange = beforeChange;
    }

    public Long getAfterChange() {
        return afterChange;
    }

    public void setAfterChange(Long afterChange) {
        this.afterChange = afterChange;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}