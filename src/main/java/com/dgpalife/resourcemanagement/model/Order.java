package com.dgpalife.resourcemanagement.model;

import java.math.BigDecimal;

public class Order {
    private Long id;

    private String status;

    private String ordersno;

    private String orderstype;

    private Long resourceid;

    private Resource resource;

    private String operator;

    private String address;

    private String newaddress;

    private String projectid;

    private Long accountdeptid;

    private Department accountdept;

    private Long usedeptid;

    private Department usedept;

    private String username;

    private String contact;

    private String chargetype;

    private BigDecimal expenses;

    private String createtime;

    private String usingnumberid;

    private Resource usingnumber;

    private Long creatorId;

    private User creator;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Department getAccountdept() {
        return accountdept;
    }

    public void setAccountdept(Department accountdept) {
        this.accountdept = accountdept;
    }

    public Department getUsedept() {
        return usedept;
    }

    public void setUsedept(Department usedept) {
        this.usedept = usedept;
    }

    public Resource getUsingnumber() {
        return usingnumber;
    }

    public void setUsingnumber(Resource usingnumber) {
        this.usingnumber = usingnumber;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrdersno() {
        return ordersno;
    }

    public void setOrdersno(String ordersno) {
        this.ordersno = ordersno;
    }

    public String getOrderstype() {
        return orderstype;
    }

    public void setOrderstype(String orderstype) {
        this.orderstype = orderstype;
    }

    public Long getResourceid() {
        return resourceid;
    }

    public void setResourceid(Long resourceid) {
        this.resourceid = resourceid;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNewaddress() {
        return newaddress;
    }

    public void setNewaddress(String newaddress) {
        this.newaddress = newaddress;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public Long getAccountdeptid() {
        return accountdeptid;
    }

    public void setAccountdeptid(Long accountdeptid) {
        this.accountdeptid = accountdeptid;
    }

    public Long getUsedeptid() {
        return usedeptid;
    }

    public void setUsedeptid(Long usedeptid) {
        this.usedeptid = usedeptid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getChargetype() {
        return chargetype;
    }

    public void setChargetype(String chargetype) {
        this.chargetype = chargetype;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUsingnumberid() {
        return usingnumberid;
    }

    public void setUsingnumberid(String usingnumberid) {
        this.usingnumberid = usingnumberid;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}