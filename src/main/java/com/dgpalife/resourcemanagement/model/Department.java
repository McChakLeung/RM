package com.dgpalife.resourcemanagement.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Department {
    private Long id;

    private Long parentdeptid;

    private String deptname;

    private String issecretary;

    private String creatorum;

    private Date createtime;

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

    private List<Department> children = new ArrayList();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentdeptid() {
        return parentdeptid;
    }

    public void setParentdeptid(Long parentdeptid) {
        this.parentdeptid = parentdeptid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getIssecretary() {
        return issecretary;
    }

    public void setIssecretary(String issecretary) {
        this.issecretary = issecretary;
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
}