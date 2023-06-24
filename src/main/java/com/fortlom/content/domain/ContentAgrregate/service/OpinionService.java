package com.fortlom.content.domain.ContentAgrregate.service;

import com.fortlom.content.domain.ContentAgrregate.entity.Opinion;
import org.springframework.http.ResponseEntity;

public interface OpinionService {
    Long getPublicationOpinionCount(Long publicationId);
    Long getEventOpinionCount(Long eventId);
    boolean getPublicationOpinionByFanatic(Long publicationId, Long fanaticId);
    boolean getEventOpinionByFanatic(Long eventId, Long fanaticId);
    Opinion createForPublication(Long publicationId, Long fanaticId, Opinion opinion);
    Opinion createForEvent(Long eventId, Long fanaticId, Opinion opinion);
    ResponseEntity<?> deleteForPublication(Long publicationId, Long fanaticId);
    ResponseEntity<?> deleteForEvent(Long eventId, Long fanaticId);
    Opinion updateForPublication(Long publicationId, Long fanaticId, Opinion opinion);
    Opinion updateForEvent(Long eventId, Long fanaticId, Opinion opinion);
}
