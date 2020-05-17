package com.dgpalife.resourcemanagement.model;

public class ChangeItemRecord {
    private Long id;

    private Long resource_id;

    private String resource_no;

    private Long item_id;

    private String item_name;

    private Long before_change;

    private Long after_change;

    private Long order_id;

    private String createtime;

    private Long creator_id;

    private String comment;

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

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Long getBefore_change() {
        return before_change;
    }

    public void setBefore_change(Long before_change) {
        this.before_change = before_change;
    }

    public Long getAfter_change() {
        return after_change;
    }

    public void setAfter_change(Long after_change) {
        this.after_change = after_change;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Long creator_id) {
        this.creator_id = creator_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}