package com.dgpalife.resourcemanagement.model;

public class EquipmentPurchaseRecord {
    private Long id;

    private Integer purchaseNum;

    private Long expenseId;

    private Expense expense;

    private Long equipment_type_id;

    private Equipment_type equipment_type;

    private Long projectId;

    private Project project;

    private Long orderId;

    private Long creatorId;

    private String createTime;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Long getEquipment_type_id() {
        return equipment_type_id;
    }

    public void setEquipment_type_id(Long equipment_type_id) {
        this.equipment_type_id = equipment_type_id;
    }

    public Equipment_type getEquipment_type() {
        return equipment_type;
    }

    public void setEquipment_type(Equipment_type equipment_type) {
        this.equipment_type = equipment_type;
    }

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

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
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