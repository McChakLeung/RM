package com.dgpalife.resourcemanagement.service;

import java.util.List;
import java.util.Map;

public interface ItemService {
    List<Object> selectItemByQueryText(Map<String, Object> params);

    int selectCountByQueryText(Map<String, Object> params);
}
