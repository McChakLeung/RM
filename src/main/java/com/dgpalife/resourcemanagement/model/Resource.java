package com.dgpalife.resourcemanagement.model;

public class Resource {
    private Long id;

    private String resourceNo;

    private String resourcType;

    private String operator;

    private Long networkRoomId;

    private Long departmentId;

    private Department department;

    private Long usedepartmentId;

    private Department usedepartment;

    private String username;

    private Long equipmentPortId;

    private Expense expense;

    private Long expenseId;

    private String createTime;

    private Long creatorId;

    private Long workplaceId;

    private Workplace workplace;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getUsedepartmentId() {
        return usedepartmentId;
    }

    public void setUsedepartmentId(Long usedepartmentId) {
        this.usedepartmentId = usedepartmentId;
    }

    public Department getUsedepartment() {
        return usedepartment;
    }

    public void setUsedepartment(Department usedepartment) {
        this.usedepartment = usedepartment;
    }

    public Long getWorkplaceId() {
        return workplaceId;
    }

    public void setWorkplaceId(Long workplaceId) {
        this.workplaceId = workplaceId;
    }

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