package com.dgpalife.resourcemanagement.model;

import java.util.List;

public class Resource {

    private Long id;

    private String resource_no;

    private String resourc_type;

    private String operator;

    private Long workplace_id;

    private Workplace workplace;

    private Long network_room_id;

    private NetworkRoom networkRoom;

    private Long department_id;

    private Department department;

    private Long usedepartment_id;

    private Department usedepartment;

    private String username;

    private Long equipment_id;

    private Equipment equipment;

    private Long equipment_port_id;

    private EquipmentPort equipmentPort;

    private Long expense_id;

    private Expense expense;

    private String account_center;

    private String create_time;

    private Long creator_id;

    private User creator;

    private List<Order> orderList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResource_no() {
        return resource_no;
    }

    public void setResource_no(String resource_no) {
        this.resource_no = resource_no;
    }

    public String getResourc_type() {
        return resourc_type;
    }

    public void setResourc_type(String resourc_type) {
        this.resourc_type = resourc_type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Long getWorkplace_id() {
        return workplace_id;
    }

    public void setWorkplace_id(Long workplace_id) {
        this.workplace_id = workplace_id;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    public Long getNetwork_room_id() {
        return network_room_id;
    }

    public void setNetwork_room_id(Long network_room_id) {
        this.network_room_id = network_room_id;
    }

    public NetworkRoom getNetworkRoom() {
        return networkRoom;
    }

    public void setNetworkRoom(NetworkRoom networkRoom) {
        this.networkRoom = networkRoom;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getUsedepartment_id() {
        return usedepartment_id;
    }

    public void setUsedepartment_id(Long usedepartment_id) {
        this.usedepartment_id = usedepartment_id;
    }

    public Department getUsedepartment() {
        return usedepartment;
    }

    public void setUsedepartment(Department usedepartment) {
        this.usedepartment = usedepartment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Long getEquipment_port_id() {
        return equipment_port_id;
    }

    public void setEquipment_port_id(Long equipment_port_id) {
        this.equipment_port_id = equipment_port_id;
    }

    public EquipmentPort getEquipmentPort() {
        return equipmentPort;
    }

    public void setEquipmentPort(EquipmentPort equipmentPort) {
        this.equipmentPort = equipmentPort;
    }

    public Long getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(Long expense_id) {
        this.expense_id = expense_id;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public String getAccount_center() {
        return account_center;
    }

    public void setAccount_center(String account_center) {
        this.account_center = account_center;
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }


}