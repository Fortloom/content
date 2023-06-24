package com.fortlom.content.application.service;
import com.fortlom.content.application.exception.ResourceNotFoundException;
import com.fortlom.content.application.exception.ResourcePerzonalized;
import com.fortlom.content.domain.ContentAgrregate.entity.Event;
import com.fortlom.content.domain.ContentAgrregate.entity.Publication;
import com.fortlom.content.domain.ContentAgrregate.repository.EventRepository;
import com.fortlom.content.domain.ContentAgrregate.repository.PublicationRepository;
import com.fortlom.content.domain.ContentAgrregate.service.EventService;
import com.fortlom.content.domain.ContentAgrregate.valueobject.Artist;
import com.fortlom.content.interfaces.dto.event.CreateEventResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private static final String ENTITY = "Event";
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Event> getAllEvents() {
        List<Event>events=eventRepository.findAll();
        for (Event event:events){
            Publication publication = restTemplate.getForObject("http://localhost:8082/api/v1/content-service/publications/"+event.getPublicationId(), Publication.class);
            event.setPublication(publication);
        }
        return events;
    }

    @Override
    public Page<Event> getAllEvents(Pageable pageable) {
        Page<Event>events=eventRepository.findAll(pageable);
        for (Event event:events){
            Long publicationId = event.getPublicationId();
            Publication publication = publicationRepository.findById(publicationId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, publicationId));
            //Publication publication = restTemplate.getForObject("http://localhost:8082/api/v1/content-service/publications/"+event.getPublicationId(), Publication.class);
            event.setPublication(publication);
        }
        return events;
    }

    @Override
    public Event getEventById(Long eventId) {
        Event event=eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, eventId));
        Publication publication = restTemplate.getForObject("http://localhost:8082/api/v1/content-service/publications/"+event.getPublicationId(), Publication.class);
        event.setPublication(publication);
        return event;
    }

    @Override
    public Event createEvent(Long artistId, Event request) {
        boolean ExistsArtist = restTemplate.getForObject("http://localhost:8081/api/v1/user-service/artists/check/" + artistId, boolean.class);
        boolean ArtistIsPremium = restTemplate.getForObject("http://localhost:8081/api/v1/user-service/artists/checkpremium/" + artistId, boolean.class);
        if(ExistsArtist && ArtistIsPremium){
            Artist artist= restTemplate.getForObject("http://localhost:8081/api/v1/user-service/artists/"+ artistId,Artist.class);
            request.setArtistId(artistId);
            return eventRepository.save(request);
        }else{
            throw  new ResourcePerzonalized("id inexistente");
        }
    }

    @Override
    public Event updateEvent(Long eventId, CreateEventResource request) {
        return eventRepository.findById(eventId).map(post->{
            post.setName(request.getName());
            post.setDescription(request.getDescription());
            post.setReleasedDate(request.getReleasedDate());
            eventRepository.save(post);
            return  post;
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, eventId));
    }

    @Override
    public List<Event> getEventsByArtistId(Long artistId) {
        boolean ExistsArtist = restTemplate.getForObject("http://localhost:8081/api/v1/user-service/artists/check/"+artistId,boolean.class);
        if(ExistsArtist){
            List<Event>events=eventRepository.findByArtistId(artistId);
            for (Event event:events){
                Publication publication = restTemplate.getForObject("http://localhost:8082/api/v1/content-service/publications/"+event.getPublicationId(), Publication.class);
                event.setPublication(publication);
            }
            return events;
        }
        else {
            throw  new ResourcePerzonalized("Artist Id was not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteEvent(Long eventId) {
        return eventRepository.findById(eventId).map(event -> {
            eventRepository.delete(event);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, eventId));
    }

    @Override
    public boolean existsById(Long eventId) {
        return eventRepository.existsById(eventId);
    }
}
