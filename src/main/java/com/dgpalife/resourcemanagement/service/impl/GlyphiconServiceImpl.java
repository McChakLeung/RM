package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.GlyphiconMapper;
import com.dgpalife.resourcemanagement.model.Glyphicon;
import com.dgpalife.resourcemanagement.service.GlyphiconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlyphiconServiceImpl implements GlyphiconService {
    @Autowired
    private GlyphiconMapper glyphiconMapper;

    @Override
    public List<Glyphicon> queryAllGlyphicon() {
        return glyphiconMapper.queryAllGlyphicon();
    }
}
