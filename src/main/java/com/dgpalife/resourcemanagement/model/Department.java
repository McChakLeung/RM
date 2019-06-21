package com.dgpalife.resourcemanagement.model;

import java.io.Serializable;
import java.util.Date;

public class Department implements Serializable{
    private Integer id;

    private String issecretary;

    private String deptno;

    private String deptname;

    private String creatorum;

    private Date createtime;

    private String parentdeptno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIssecretary() {
        return issecretary;
    }

    public void setIssecretary(String issecretary) {
        this.issecretary = issecretary;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getCreatorum() {
        return creatorum;
    }

    public void setCreatorum(String creatorum) {
        this.creatorum = creatorum;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getParentdeptno() {
        return parentdeptno;
    }

    public void setParentdeptno(String parentdeptno) {
        this.parentdeptno = parentdeptno;
    }
}