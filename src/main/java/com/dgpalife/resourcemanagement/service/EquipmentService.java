package com.dgpalife.resourcemanagement.service;

import java.util.List;
import java.util.Map;

public interface EquipmentService {
    int queryByEquipmentSn(String equipmentSn);

    List<Object> selectEquipmentByQueryText(Map<String, Object> params);

    int selectCountByQueryText(Map<String, Object> params);
}
