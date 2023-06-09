package com.fortlom.content.domain.ContentAgrregate.service;

import com.fortlom.content.domain.ContentAgrregate.entity.Opinion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PublicationService {
    List<Opinion> getAll();
    Page<Opinion> getAll(Pageable pageable);
    Opinion getById(Long publicationId);
    Opinion create(Long artistId, Opinion opinion, String type);
    Opinion update(Long publicationId, Opinion request);
    List<Opinion> getPublicationByArtistId(Long artistId);
    ResponseEntity<?> delete(Long publicationId);
    boolean existspublication(Long publicationId);
}
