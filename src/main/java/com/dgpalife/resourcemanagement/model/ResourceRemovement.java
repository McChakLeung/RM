package com.dgpalife.resourcemanagement.model;

public class ResourceRemovement {
    private Long id;

    private Long resource_id;

    private Resource resource;

    private String resource_no;

    private String resourc_type;

    private String operator;

    private String workplaceName;

    private String networkroom_name;

    private String departmentName;

    private String usedepartmentName;

    private String username;

    private Order order;

    private Long order_id;

    private Long project_id;

    private Long template_id;

    private Long creator_id;

    private String create_time;

    public String getNetworkroom_name() {
        return networkroom_name;
    }

    public void setNetworkroom_name(String networkroom_name) {
        this.networkroom_name = networkroom_name;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResource_id() {
        return resource_id;
    }

    public void setResource_id(Long resource_id) {
        this.resource_id = resource_id;
    }

    public String getResource_no() {
        return resource_no;
    }

    public void setResource_no(String resource_no) {
        this.resource_no = resource_no;
    }

    public String getResourc_type() {
        return resourc_type;
    }

    public void setResourc_type(String resourc_type) {
        this.resourc_type = resourc_type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getWorkplaceName() {
        return workplaceName;
    }

    public void setWorkplaceName(String workplaceName) {
        this.workplaceName = workplaceName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getUsedepartmentName() {
        return usedepartmentName;
    }

    public void setUsedepartmentName(String usedepartmentName) {
        this.usedepartmentName = usedepartmentName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public Long getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(Long template_id) {
        this.template_id = template_id;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Long creator_id) {
        this.creator_id = creator_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}