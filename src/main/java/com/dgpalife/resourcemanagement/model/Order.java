package com.dgpalife.resourcemanagement.model;

public class Order {
    private Long id;

    private String type;

    private String status;

    private String title;

    private Long approvalLinkId;

    private Long applyDepartmentId;

    private Long proposerId;

    private String createTime;

    private String reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getApprovalLinkId() {
        return approvalLinkId;
    }

    public void setApprovalLinkId(Long approvalLinkId) {
        this.approvalLinkId = approvalLinkId;
    }

    public Long getApplyDepartmentId() {
        return applyDepartmentId;
    }

    public void setApplyDepartmentId(Long applyDepartmentId) {
        this.applyDepartmentId = applyDepartmentId;
    }

    public Long getProposerId() {
        return proposerId;
    }

    public void setProposerId(Long proposerId) {
        this.proposerId = proposerId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}