package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.ConstructDetailMapper;
import com.dgpalife.resourcemanagement.model.ConstructDetail;
import com.dgpalife.resourcemanagement.service.ConstructDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConstructDetailServiceImpl implements ConstructDetailService {

    @Autowired
    private ConstructDetailMapper constructDetailMapper;

    @Override
    public void saveConstructDetailBatch(List<ConstructDetail> constructDetailList) {
        for(ConstructDetail constructDetail:constructDetailList){
            constructDetailMapper.insertSelective(constructDetail);
        }
    }

    @Override
    public int getConstructDetailNumByOrderId(Long order_id) {
        return constructDetailMapper.getConstructDetailNumByOrderId(order_id);
    }
}
