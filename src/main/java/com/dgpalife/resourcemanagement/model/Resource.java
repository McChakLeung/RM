package com.dgpalife.resourcemanagement.model;

import java.util.Date;

public class Resource {
    private Long id;

    private String resourceno;

    private String resourcetype;

    private String servicesupplier;

    private String address;

    private Long accountdeptid;

    public Department getAccountdept() {
        return accountdept;
    }

    public void setAccountdept(Department accountdept) {
        this.accountdept = accountdept;
    }

    public Department getUserdept() {
        return userdept;
    }

    public void setUserdept(Department userdept) {
        this.userdept = userdept;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Department accountdept;

    private Long userdeptid;

    private Department userdept;

    private String username;

    private Long equipmentid;

    private Double expenses;

    private String chargetype;

    private Long userId;

    private User user;

    private Date createtime;

    private String resourcestatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceno() {
        return resourceno;
    }

    public void setResourceno(String resourceno) {
        this.resourceno = resourceno;
    }

    public String getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(String resourcetype) {
        this.resourcetype = resourcetype;
    }

    public String getServicesupplier() {
        return servicesupplier;
    }

    public void setServicesupplier(String servicesupplier) {
        this.servicesupplier = servicesupplier;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAccountdeptid() {
        return accountdeptid;
    }

    public void setAccountdeptid(Long accountdeptid) {
        this.accountdeptid = accountdeptid;
    }

    public Long getUserdeptid() {
        return userdeptid;
    }

    public void setUserdeptid(Long userdeptid) {
        this.userdeptid = userdeptid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getEquipmentid() {
        return equipmentid;
    }

    public void setEquipmentid(Long equipmentid) {
        this.equipmentid = equipmentid;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }

    public String getChargetype() {
        return chargetype;
    }

    public void setChargetype(String chargetype) {
        this.chargetype = chargetype;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getResourcestatus() {
        return resourcestatus;
    }

    public void setResourcestatus(String resourcestatus) {
        this.resourcestatus = resourcestatus;
    }
}