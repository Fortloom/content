package com.fortlom.content.interfaces.mapping.entity;

import com.fortlom.content.domain.ContentAgrregate.entity.Opinion;
import com.fortlom.content.interfaces.dto.opinion.CreateOpinionResource;
import com.fortlom.content.interfaces.dto.opinion.OpinionResource;
import com.fortlom.content.interfaces.mapping.configuration.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class OpinionMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public OpinionResource toResource(Opinion model) {
        return mapper.map(model, OpinionResource.class);
    }

    public Page<OpinionResource> modelListToPage(List<Opinion> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, OpinionResource.class), pageable, modelList.size());
    }

    public Opinion toModel(CreateOpinionResource resource) {
        return mapper.map(resource, Opinion.class);
    }
}
