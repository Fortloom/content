package com.fortlom.content.domain.ContentAgrregate.repository;

import com.fortlom.content.domain.ContentAgrregate.entity.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    Long countByPublicationId(Long publicationId);
    Long countByEventId(Long eventId);
    Optional<Opinion> findByPublicationIdAndFanaticId(Long publicationId, @NotNull Long fanaticId);
    Optional<Opinion> findByEventIdAndFanaticId(Long eventId, @NotNull Long fanaticId);
}
