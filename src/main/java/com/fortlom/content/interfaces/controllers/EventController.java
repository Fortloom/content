package com.fortlom.content.interfaces.controllers;


import com.fortlom.content.domain.ContentAgrregate.entity.Event;
import com.fortlom.content.domain.ContentAgrregate.service.EventService;
import com.fortlom.content.interfaces.dto.event.CreateEventResource;
import com.fortlom.content.interfaces.dto.event.EventResource;
import com.fortlom.content.interfaces.mapping.entity.EventMapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/contentservice")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private EventMapper mapper;

    @Autowired
    private ModelMapper mapping;

    @GetMapping("/events")
    public Page<EventResource> getAllEvents(Pageable pageable) {
        return mapper.modelListToPage(eventService.getAllEvents(), pageable);
    }
    @GetMapping("/event/{eventId}")
    public EventResource getEventById(@PathVariable Long eventId) {
        return mapper.toResource(eventService.getEventById(eventId));
    }

    @GetMapping("/artist/{artistId}/events")
    public ResponseEntity<Page<EventResource>> getAllEventsByArtistId(@PathVariable Long artistId,Pageable pageable) {

        return ResponseEntity.ok(mapper.modelListToPage(eventService.getEventsByArtistId(artistId), pageable));
    }


    @PostMapping("/artist/{artistId}/events")
    public ResponseEntity<EventResource> createEvent(@PathVariable Long artistId,@RequestBody CreateEventResource request) {
        Event event = mapping.map(request, Event.class);
        return ResponseEntity.ok(mapping.map(eventService.createEvent(artistId, event), EventResource.class));
    }

    @DeleteMapping("/event/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId) {
        return eventService.deleteEvent(eventId);
    }

    @PutMapping("/eventupdatereleseadedate/{eventId}/releasedate/{releasedate}")
    public EventResource updateEventreleaseddate(@PathVariable Long eventId,@PathVariable String releasedate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date dataFormateada = format.parse(releasedate);
        return mapper.toResource(eventService.updateEventreleasedate(eventId,dataFormateada));
    }
    @GetMapping("events/check/{eventId}")
    public boolean existspublication(@PathVariable("eventId") Long eventId){
        return eventService.existsById(eventId);
    }

}
