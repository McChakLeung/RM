package com.dgpalife.resourcemanagement.model;

public class EquipmentPort {
    private Long id;

    private String port_type;

    private Integer port_no;

    private Long equipment_id;

    private Equipment equipment;

    private Long resource_id;

    private Resource resource;

    private String create_time;

    private Long creator_id;

    private User cretor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPort_type() {
        return port_type;
    }

    public void setPort_type(String port_type) {
        this.port_type = port_type;
    }

    public Integer getPort_no() {
        return port_no;
    }

    public void setPort_no(Integer port_no) {
        this.port_no = port_no;
    }

    public Long getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Long equipment_id) {
        this.equipment_id = equipment_id;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Long getResource_id() {
        return resource_id;
    }

    public void setResource_id(Long resource_id) {
        this.resource_id = resource_id;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
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

    public User getCretor() {
        return cretor;
    }

    public void setCretor(User cretor) {
        this.cretor = cretor;
    }
}