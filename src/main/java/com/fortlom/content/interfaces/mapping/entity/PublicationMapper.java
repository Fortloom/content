package com.fortlom.content.interfaces.mapping.entity;
import com.fortlom.content.domain.ContentAgrregate.entity.Opinion;
import com.fortlom.content.interfaces.dto.publication.CreatePublicationResource;
import com.fortlom.content.interfaces.dto.publication.PublicationResource;
import com.fortlom.content.interfaces.mapping.configuration.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.io.Serializable;
import java.util.List;
public class PublicationMapper implements Serializable{
    @Autowired
    EnhancedModelMapper mapper;

    public PublicationResource toResource(Opinion model) {
        return mapper.map(model, PublicationResource.class);
    }

    public Page<PublicationResource> modelListToPage(List<Opinion> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, PublicationResource.class), pageable, modelList.size());
    }
    public Opinion toModel(CreatePublicationResource resource) {

        return mapper.map(resource, Opinion.class);
    }

}
