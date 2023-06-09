package com.fortlom.content.domain.ContentAgrregate.repository;
import com.fortlom.content.domain.ContentAgrregate.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long>{
    List<Event>findByArtistid(Long artistId);


    boolean existsById(Long eventId);
}
