package com.fortlom.content.interfaces.mapping.entity;
import com.fortlom.content.domain.ContentAgrregate.entity.Event;
import com.fortlom.content.interfaces.dto.event.CreateEventResource;
import com.fortlom.content.interfaces.dto.event.EventResource;
import com.fortlom.content.interfaces.mapping.configuration.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
public class EventMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public EventResource toResource(Event model) {
        return mapper.map(model, EventResource.class);
    }

    public Page<EventResource> modelListToPage(List<Event> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, EventResource.class), pageable, modelList.size());
    }

    public Event toModel(CreateEventResource resource) {
        return mapper.map(resource, Event.class);
    }
}
