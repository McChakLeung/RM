package com.dgpalife.resourcemanagement.model;

public class ConstructDetail {
    private Long id;

    private Long usedepartment_id;

    private Department usedepartment;

    private String usedepartment_type;

    private String operator;

    private String resourceType;

    private Integer number;

    private Long expenseId;

    private Expense expense;

    private Long workplaceId;

    private Workplace workplace;

    private Long networkRoomId;

    private NetworkRoom networkRoom;

    private Long orderId;

    private Order order;

    private Long templateId;

    private Template template;

    private String createTime;

    private Long creatorId;

    private User user;

    private Long projectId;

    private Project project;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getUsedepartment_id() {
        return usedepartment_id;
    }

    public void setUsedepartment_id(Long usedepartment_id) {
        this.usedepartment_id = usedepartment_id;
    }

    public Department getUsedepartment() {
        return usedepartment;
    }

    public void setUsedepartment(Department usedepartment) {
        this.usedepartment = usedepartment;
    }

    public String getUsedepartment_type() {
        return usedepartment_type;
    }

    public void setUsedepartment_type(String usedepartment_type) {
        this.usedepartment_type = usedepartment_type;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    public NetworkRoom getNetworkRoom() {
        return networkRoom;
    }

    public void setNetworkRoom(NetworkRoom networkRoom) {
        this.networkRoom = networkRoom;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Long getNetworkRoomId() {
        return networkRoomId;
    }

    public void setNetworkRoomId(Long networkRoomId) {
        this.networkRoomId = networkRoomId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
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

    public Long getWorkplaceId() {
        return workplaceId;
    }

    public void setWorkplaceId(Long workplaceId) {
        this.workplaceId = workplaceId;
    }
}