package com.fortlom.content.domain.ContentAgrregate.repository;

import com.fortlom.content.domain.ContentAgrregate.entity.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    Long countByPublicationId(Long publicationId);
    Long countByPublicationIdAndIsAgree(Long publicationId, Boolean isAgree);
    Long countByEventId(Long eventId);
    Long countByEventIdAndIsAgree(Long eventId, Boolean isAgree);
    Optional<Opinion> findByPublicationIdAndFanaticId(Long publicationId, @NotNull Long fanaticId);
    Optional<Opinion> findByEventIdAndFanaticId(Long eventId, @NotNull Long fanaticId);
}
