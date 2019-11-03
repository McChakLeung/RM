package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.ProjectMapper;
import com.dgpalife.resourcemanagement.model.Project;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Page<Object> selectProjectList(Map<String, Object> params) {
        Page<Object> page = new Page<>((Integer) params.get("pageno"),(Integer)params.get("pagesize"));
        //查询项目列表数据
        params.put("startline",page.getStartline());
        List<Map<String,Object>> datas = projectMapper.selectProjectList(params);

        page.setDatalist(datas);

        //查询项目总数
        Integer totalsize = projectMapper.selectCount(params);
        //将查询结果存放到公共的Page类中
        page.setTotalsize(totalsize);
        return page;
    }

    @Override
    public int saveProject(Project project) {
        return projectMapper.insertSelective(project);
    }

    @Override
    public Project selectProjectById(Long id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateProjectById(Project project) {
        return projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public int deleteProjectById(Long id) {
        return projectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Object> selectProjectByQueryText(Map<String, Object> params) {
        return projectMapper.selectProjectByQueryText(params);
    }

    @Override
    public int selectCountByQueryText(Map<String, Object> params) {
        return projectMapper.selectCountByQueryText(params);
    }
}
