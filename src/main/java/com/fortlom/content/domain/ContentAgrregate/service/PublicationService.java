package com.fortlom.content.domain.ContentAgrregate.service;

import com.fortlom.content.domain.ContentAgrregate.entity.Opinion;
import com.fortlom.content.domain.ContentAgrregate.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PublicationService {
    List<Publication> getAll();
    Page<Publication> getAll(Pageable pageable);
    Publication getById(Long publicationId);
    Publication create(Long artistId, Publication publication);
    Publication update(Long publicationId, Publication request);
    List<Publication> getPublicationByArtistId(Long artistId);
    ResponseEntity<?> delete(Long publicationId);
    boolean existsPublication(Long publicationId);
}
