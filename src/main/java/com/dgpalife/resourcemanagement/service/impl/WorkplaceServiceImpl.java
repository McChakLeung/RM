package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.WorkplaceMapper;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.model.Workplace;
import com.dgpalife.resourcemanagement.service.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkplaceServiceImpl implements WorkplaceService {

    @Autowired
    private WorkplaceMapper workplaceMapper;

    @Override
    public Page<Workplace> selectWorkplaceList(Map<String, Object> params) {
        Page<Workplace> page = new Page<>((Integer) params.get("pageno"),(Integer)params.get("pagesize"));
        //查询用户列表数据
        params.put("startline",page.getStartline());
        List<Workplace> datas = workplaceMapper.selectWorkplaceList(params);

        page.setDatalist(datas);

        //查询用户总数
        Integer totalsize = workplaceMapper.selectCount(params);
        //将查询结果存放到公共的Page类中
        page.setTotalsize(totalsize);
        return page;
    }

    @Override
    public int saveWorkplace(Workplace workplace) {
        return workplaceMapper.insertSelective(workplace);
    }
}
