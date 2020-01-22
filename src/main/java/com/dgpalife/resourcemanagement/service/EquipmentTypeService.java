package com.dgpalife.resourcemanagement.service;

import java.util.List;
import java.util.Map;

public interface EquipmentTypeService {
    List<Object> queryEquipmentType(Map<String,Object> params);

    int selectCount(Map<String,Object> params);
}
