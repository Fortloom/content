package com.fortlom.content.domain.ContentAgrregate.repository;
import com.fortlom.content.domain.ContentAgrregate.entity.Opinion;
import com.fortlom.content.domain.ContentAgrregate.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long>{
    List<Publication> findByArtistId(Long artistId);

    boolean existsById(Long id);
}
