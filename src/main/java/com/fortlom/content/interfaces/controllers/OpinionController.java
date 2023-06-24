package com.fortlom.content.interfaces.controllers;

import com.fortlom.content.domain.ContentAgrregate.entity.Opinion;
import com.fortlom.content.domain.ContentAgrregate.service.OpinionService;
import com.fortlom.content.interfaces.dto.opinion.CreateOpinionResource;
import com.fortlom.content.interfaces.dto.opinion.OpinionResource;
import com.fortlom.content.interfaces.mapping.entity.OpinionMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/content-service")
public class OpinionController {
    @Autowired
    private OpinionService opinionService;

    @Autowired
    OpinionMapper mapper;

    @Autowired
    private ModelMapper mapping;

    @GetMapping("publications/{publicationId}/opinions")
    public Long getPublicationOpinionCount(@PathVariable Long publicationId){
        return opinionService.getPublicationOpinionCount(publicationId);
    }

    @GetMapping("events/{eventId}/opinions")
    public Long getEventOpinionCount(@PathVariable Long eventId){
        return opinionService.getEventOpinionCount(eventId);
    }

    @GetMapping("publications/{publicationId}/opinions/{fanaticId}")
    public boolean getPublicationOpinionByFanatic(@PathVariable Long publicationId, @PathVariable Long fanaticId){
        return opinionService.getPublicationOpinionByFanatic(publicationId, fanaticId);
    }

    @GetMapping("events/{eventId}/opinions/{fanaticId}")
    public boolean getEventOpinionByFanatic(@PathVariable Long eventId, @PathVariable Long fanaticId){
        return opinionService.getEventOpinionByFanatic(eventId, fanaticId);
    }

    @PostMapping("publications/{publicationId}/opinions/{fanaticId}")
    public ResponseEntity<OpinionResource> createOpinionForPublication(@PathVariable Long publicationId, @PathVariable Long fanaticId, @RequestBody CreateOpinionResource resource){
        Opinion opinion = mapping.map(resource, Opinion.class);
        return ResponseEntity.ok(mapping.map(opinionService.createForPublication(publicationId, fanaticId, opinion), OpinionResource.class));
    }

    @PostMapping("events/{eventId}/opinions/{fanaticId}")
    public ResponseEntity<OpinionResource> createOpinionForEvent(@PathVariable Long eventId, @PathVariable Long fanaticId, @RequestBody CreateOpinionResource resource){
        Opinion opinion = mapping.map(resource, Opinion.class);
        return ResponseEntity.ok(mapping.map(opinionService.createForEvent(eventId, fanaticId, opinion), OpinionResource.class));
    }


    @DeleteMapping("publications/{publicationId}/opinions/{fanaticId}")
    public ResponseEntity<?> deleteOpinionForPublication(@PathVariable Long publicationId, @PathVariable Long fanaticId){
        return opinionService.deleteForPublication(publicationId, fanaticId);
    }

    @DeleteMapping("events/{eventId}/opinions/{fanaticId}")
    public ResponseEntity<?> deleteOpinionForEvent(@PathVariable Long eventId, @PathVariable Long fanaticId){
        return opinionService.deleteForEvent(eventId, fanaticId);
    }


    @PutMapping("publications/{publicationId}/opinions/{fanaticId}")
    public ResponseEntity<OpinionResource> updateOpinionForPublication(@PathVariable Long publicationId, @PathVariable Long fanaticId, @RequestBody CreateOpinionResource resource){
        Opinion opinion = mapping.map(resource, Opinion.class);
        return ResponseEntity.ok(mapping.map(opinionService.updateForPublication(publicationId, fanaticId, opinion), OpinionResource.class));
    }

    @PutMapping("events/{eventId}/opinions/{fanaticId}")
    public ResponseEntity<OpinionResource> updateOpinionForEvent(@PathVariable Long eventId, @PathVariable Long fanaticId, @RequestBody CreateOpinionResource resource){
        Opinion opinion = mapping.map(resource, Opinion.class);
        return ResponseEntity.ok(mapping.map(opinionService.updateForEvent(eventId, fanaticId, opinion), OpinionResource.class));
    }
}
