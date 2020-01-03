package com.dgpalife.resourcemanagement.model;

import java.util.List;

public class Order {
    private Long id;

    private String type;

    private String status;

    private String title;

//    private Long approvalLinkId;

    private Long applyDepartmentId;

    private Department applyDepartment;

//    private Long applyUseDepartmentId;
//
//    private Department applyUseDepartment;

    private Long proposerId;

    private User proposer;

    private String createTime;

    private String reason;

    private Long projectId;

    private Project project;

    private String piid;

    private String approval_opinion;

    public String getApproval_opinion() {
        return approval_opinion;
    }

    public void setApproval_opinion(String approval_opinion) {
        this.approval_opinion = approval_opinion;
    }

    public String getPiid() {
        return piid;
    }

    public void setPiid(String piid) {
        this.piid = piid;
    }
    //    public Long getApplyUseDepartmentId() {
//        return applyUseDepartmentId;
//    }
//
//    public void setApplyUseDepartmentId(Long applyUseDepartmentId) {
//        this.applyUseDepartmentId = applyUseDepartmentId;
//    }
//
//    public Department getApplyUseDepartment() {
//        return applyUseDepartment;
//    }
//
//    public void setApplyUseDepartment(Department applyUseDepartment) {
//        this.applyUseDepartment = applyUseDepartment;
//    }

    private List<ConstructDetail> constructDetailList;

    private List<ResourceMigration> resourceMigrationList;

    private List<ResourceRemovement> resourceRemovementList;

    private List<ChangeItemRecord> changeItemRecordList;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Department getApplyDepartment() {
        return applyDepartment;
    }

    public void setApplyDepartment(Department applyDepartment) {
        this.applyDepartment = applyDepartment;
    }

    public User getProposer() {
        return proposer;
    }

    public void setProposer(User proposer) {
        this.proposer = proposer;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public List<ConstructDetail> getConstructDetailList() {
        return constructDetailList;
    }

    public void setConstructDetailList(List<ConstructDetail> constructDetailList) {
        this.constructDetailList = constructDetailList;
    }

    public List<ResourceMigration> getResourceMigrationList() {
        return resourceMigrationList;
    }

    public void setResourceMigrationList(List<ResourceMigration> resourceMigrationList) {
        this.resourceMigrationList = resourceMigrationList;
    }

    public List<ResourceRemovement> getResourceRemovementList() {
        return resourceRemovementList;
    }

    public void setResourceRemovementList(List<ResourceRemovement> resourceRemovementList) {
        this.resourceRemovementList = resourceRemovementList;
    }

    public List<ChangeItemRecord> getChangeItemRecordList() {
        return changeItemRecordList;
    }

    public void setChangeItemRecordList(List<ChangeItemRecord> changeItemRecordList) {
        this.changeItemRecordList = changeItemRecordList;
    }

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

//    public Long getApprovalLinkId() {
//        return approvalLinkId;
//    }
//
//    public void setApprovalLinkId(Long approvalLinkId) {
//        this.approvalLinkId = approvalLinkId;
//    }

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