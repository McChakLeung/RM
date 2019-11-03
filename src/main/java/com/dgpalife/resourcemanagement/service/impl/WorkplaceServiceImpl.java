package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.NetworkRoomMapper;
import com.dgpalife.resourcemanagement.mapper.WorkplaceMapper;
import com.dgpalife.resourcemanagement.model.NetworkRoom;
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

    @Autowired
    private NetworkRoomMapper networkRoomMapper;

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

    @Override
    public Workplace selectWorkplaceById(Long id) {
        return workplaceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateWorkplace(Workplace workplace) {
        return workplaceMapper.updateByPrimaryKeySelective(workplace);
    }

    /**
     * 级联删除职场及机房信息
     * @param id
     */
    @Override
    public void deleteWorkplaceAndNetworkRoomByWorkplaceId(Long id) {
        //删除职场信息
        workplaceMapper.deleteByPrimaryKey(id);

        //查询该职场关联的机房信息
        List<NetworkRoom> networkRoomList = networkRoomMapper.selectNetworkRoomListByWorkplaceId(id);

        //删除机房信息
        for(NetworkRoom networkRoom: networkRoomList){
            networkRoomMapper.deleteByPrimaryKey(networkRoom.getId());
        }

    }

    @Override
    public int selectCount(Map<String,Object> params) {
        return workplaceMapper.selectNum(params);
    }

    @Override
    public List<Object> selectWorkplaceByQueryText(Map<String,Object> params) {
        return workplaceMapper.selectWorkplaceByQueryText(params);
    }
}
