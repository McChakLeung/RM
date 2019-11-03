package com.dgpalife.resourcemanagement.vo;

import java.util.List;

public class DepartmentTreeVO {

    private Long id;

    private String title;

    private String field;

    private List<DepartmentTreeVO> children;

    private String href;

    private Boolean spread  = false;

    private Boolean checked = false;

    private Boolean disabled = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<DepartmentTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<DepartmentTreeVO> children) {
        this.children = children;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
