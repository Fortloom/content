package com.fortlom.content.application.service;

import com.fortlom.content.application.exception.ResourceNotFoundException;
import com.fortlom.content.application.exception.ResourcePerzonalized;
import com.fortlom.content.domain.ContentAgrregate.entity.Opinion;
import com.fortlom.content.domain.ContentAgrregate.repository.OpinionRepository;
import com.fortlom.content.domain.ContentAgrregate.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OpinionServiceImpl implements OpinionService {
    private static final String ENTITY = "Opinion";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OpinionRepository opinionRepository;

    private boolean existsPublication(Long publicationId){
        return Boolean.TRUE.equals(restTemplate.getForObject("http://localhost:8082/api/v1/content-service/publications/check/" + publicationId, boolean.class));
    }

    private boolean existsEvent(Long eventId){
        return Boolean.TRUE.equals(restTemplate.getForObject("http://localhost:8082/api/v1/content-service/events/check/" + eventId, boolean.class));
    }

    private boolean existsFanatic(Long fanaticId){
        return Boolean.TRUE.equals(restTemplate.getForObject("http://localhost:8081/api/v1/user-service/fanatics/check/" + fanaticId, boolean.class));
    }

    @Override
    public Long getPublicationOpinionCount(Long publicationId) {
        if (existsPublication(publicationId)){
            Long positiveOpinionCount = opinionRepository.countByPublicationIdAndIsAgree(publicationId, true);
            Long negativeOpinionCount = opinionRepository.countByPublicationIdAndIsAgree(publicationId, false);
            return positiveOpinionCount - negativeOpinionCount;
        }
        else{
            throw new ResourcePerzonalized("Publication was not found");
        }
    }

    @Override
    public Long getEventOpinionCount(Long eventId) {
        if (existsEvent(eventId)){
            Long positiveOpinionCount = opinionRepository.countByEventIdAndIsAgree(eventId, true);
            Long negativeOpinionCount = opinionRepository.countByEventIdAndIsAgree(eventId, false);
            return positiveOpinionCount - negativeOpinionCount;
        }
        else{
            throw new ResourcePerzonalized("Event was not found");
        }
    }

    @Override
    public boolean getPublicationOpinionByFanatic(Long publicationId, Long fanaticId) {
        if (existsPublication(publicationId) && existsFanatic(fanaticId)){
            Optional<Opinion> opinion = opinionRepository.findByPublicationIdAndFanaticId(publicationId, fanaticId);
            if (opinion.isPresent()){
                return opinion.get().getIsAgree();
            }
            return false;
        }
        else{
            throw new ResourcePerzonalized("Publication or Fanatic were not found");
        }
    }

    @Override
    public boolean getEventOpinionByFanatic(Long eventId, Long fanaticId) {
        if (!existsEvent(eventId)){
            throw new ResourcePerzonalized("Event was not found");
        }
        if (!existsFanatic(fanaticId)){
            throw new ResourcePerzonalized("Fanatic was not found");
        }

        Optional<Opinion> opinion = opinionRepository.findByEventIdAndFanaticId(eventId, fanaticId);
        if (opinion.isPresent()){
            return opinion.get().getIsAgree();
        }
        return false;
    }

    @Override
    public Opinion createForPublication(Long publicationId, Long fanaticId, Opinion opinion) {
        if (existsPublication(publicationId) && existsFanatic(fanaticId)){
            opinion.setFanaticId(fanaticId);
            opinion.setPublicationId(publicationId);
            return opinionRepository.save(opinion);
        }
        else{
            throw new ResourcePerzonalized("Publication or Fanatic were not found");
        }
    }

    @Override
    public Opinion createForEvent(Long eventId, Long fanaticId, Opinion opinion) {
        if (existsEvent(eventId) && existsFanatic(fanaticId)){
            opinion.setFanaticId(fanaticId);
            opinion.setEventId(eventId);
            return opinionRepository.save(opinion);
        }
        else{
            throw new ResourcePerzonalized("Publication or Fanatic were not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteForPublication(Long publicationId, Long fanaticId) {
        return opinionRepository.findByPublicationIdAndFanaticId(publicationId, fanaticId).map(opinion -> {
            opinionRepository.delete(opinion);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, publicationId));
    }

    @Override
    public ResponseEntity<?> deleteForEvent(Long eventId, Long fanaticId) {
        return opinionRepository.findByEventIdAndFanaticId(eventId, fanaticId).map(opinion -> {
            opinionRepository.delete(opinion);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, eventId));
    }

    @Override
    public Opinion updateForPublication(Long publicationId, Long fanaticId, Opinion opinion) {
        return opinionRepository.findByPublicationIdAndFanaticId(publicationId, fanaticId).map(existentOpinion->{
            existentOpinion.setIsAgree(opinion.getIsAgree());
            opinionRepository.save(existentOpinion);
            return existentOpinion;
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, publicationId));
    }

    @Override
    public Opinion updateForEvent(Long eventId, Long fanaticId, Opinion opinion) {
        return opinionRepository.findByEventIdAndFanaticId(eventId, fanaticId).map(existentOpinion->{
            existentOpinion.setIsAgree(opinion.getIsAgree());
            opinionRepository.save(existentOpinion);
            return existentOpinion;
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, eventId));
    }
}
