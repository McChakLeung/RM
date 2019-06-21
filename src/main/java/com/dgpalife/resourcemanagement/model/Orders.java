package com.dgpalife.resourcemanagement.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Orders  implements Serializable{
    private Long id;

    private String ordersno;

    private String orderstype;

    private String resourceno;

    private String resourcetype;

    private String operator;

    private String address;

    private String newaddress;

    private String project;

    private String accountdeptno;

    private String usedeptno;

    private String username;

    private String contact;

    private String chargetype;

    private BigDecimal expenses;

    private String creatorname;

    private Date createtime;

    private String status;

    private String usingnumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getAccountdeptno() {
        return accountdeptno;
    }

    public void setAccountdeptno(String accountdeptno) {
        this.accountdeptno = accountdeptno;
    }

    public String getUsedeptno() {
        return usedeptno;
    }

    public void setUsedeptno(String usedeptno) {
        this.usedeptno = usedeptno;
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

    public String getCreatorname() {
        return creatorname;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsingnumber() {
        return usingnumber;
    }

    public void setUsingnumber(String usingnumber) {
        this.usingnumber = usingnumber;
    }
}