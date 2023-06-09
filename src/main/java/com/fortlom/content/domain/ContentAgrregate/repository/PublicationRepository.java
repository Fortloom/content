package com.fortlom.content.domain.ContentAgrregate.repository;
import com.fortlom.content.domain.ContentAgrregate.entity.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Opinion,Long>{
    List<Opinion> findByArtistid(Long artistId);

    boolean existsById(Long publicationId);
}
