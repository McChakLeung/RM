package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.ItemMapper;
import com.dgpalife.resourcemanagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Object> selectItemByQueryText(Map<String, Object> params) {
        return itemMapper.selectItemByQueryText(params);
    }

    @Override
    public int selectCountByQueryText(Map<String, Object> params) {
        return itemMapper.selectCountByQueryText(params);
    }
}
