package com.dgpalife.resourcemanagement.model;

public class Item {
    private Long id;

    private String item_name;

    private String table_field;

    private String table_names;

    private String create_time;

    private Long creator_id;

    private String change_type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getTable_field() {
        return table_field;
    }

    public void setTable_field(String table_field) {
        this.table_field = table_field;
    }

    public String getTable_names() {
        return table_names;
    }

    public void setTable_names(String table_names) {
        this.table_names = table_names;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Long creator_id) {
        this.creator_id = creator_id;
    }

    public String getChange_type() {
        return change_type;
    }

    public void setChange_type(String change_type) {
        this.change_type = change_type;
    }
}