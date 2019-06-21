package com.dgpalife.resourcemanagement.model;

import java.io.Serializable;
import java.util.Date;

public class Resources implements Serializable {
    private Integer id;

    private String resourceno;

    private String resourcetype;

    private String servicesupplier;

    private String address;

    private String deptno;

    private String UserName;

    private String equipno;

    private Double expenses;

    private String chargetype;

    private String CreatorName;

    private Date createtime;

    private String resourcestatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getEquipno() {
        return equipno;
    }

    public void setEquipno(String equipno) {
        this.equipno = equipno;
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

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String CreatorName) {
        this.CreatorName = CreatorName;
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