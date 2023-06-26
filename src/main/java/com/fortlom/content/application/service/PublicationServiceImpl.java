package com.fortlom.content.application.service;
import com.fortlom.content.application.exception.ResourceNotFoundException;
import com.fortlom.content.application.exception.ResourcePerzonalized;


import com.fortlom.content.domain.ContentAgrregate.entity.Opinion;
import com.fortlom.content.domain.ContentAgrregate.entity.Publication;
import com.fortlom.content.domain.ContentAgrregate.repository.PublicationRepository;
import com.fortlom.content.domain.ContentAgrregate.service.PublicationService;
import com.fortlom.content.domain.ContentAgrregate.valueobject.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;


@Service
public class PublicationServiceImpl implements PublicationService {

    private static final String ENTITY = "Publication";
    private static final String ENTITY2 = "Artist";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public List<Publication> getAll() {
        List<Publication> publications = publicationRepository.findAll();
        for (Publication publication : publications){
            Artist artist= restTemplate.getForObject("http://localhost:8081/api/v1/user-service/artists/"+ publication.getArtistId(),Artist.class);
            publication.setArtist(artist);
        }
        return publications;
    }

    @Override
    public Page<Publication> getAll(Pageable pageable) {
        Page<Publication> publications = publicationRepository.findAll(pageable);
        for (Publication publication : publications){
            Artist artist= restTemplate.getForObject("http://localhost:8081/api/v1/user-service/artists/"+ publication.getArtistId(),Artist.class);
            publication.setArtist(artist);
        }
        return publications;
    }

    @Override
    public Publication getById(Long publicationId) {
        Publication publication =publicationRepository.findById(publicationId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, publicationId));
        Artist artist= restTemplate.getForObject("http://localhost:8081/api/v1/user-service/artists/"+ publication.getArtistId(),Artist.class);
        publication.setArtist(artist);
        return publication;
    }

    @Override
    public Publication create(Long artistId, Publication request) {
        boolean check= restTemplate.getForObject("http://localhost:8081/api/v1/user-service/artists/check/"+artistId,boolean.class);
        if(check){
            Date date = new Date();
            request.setArtistId(artistId);
            request.setRegisterDate(date);
            //if(type.equals("true")){
            //    request.setImage(true);
            //}else {
            //    request.setImage(false);
            //}
            return publicationRepository.save(request);
        }else {
            throw  new ResourcePerzonalized("Artist id was not found");
        }
    }

    @Override
    public Publication update(Long publicationId, Publication request) {
        return publicationRepository.findById(publicationId).map(post->{
            post.setDescription(request.getDescription());
            publicationRepository.save(post);
            return post;
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, publicationId));
    }

    @Override
    public List<Publication> getPublicationByArtistId(Long artistId) {
        boolean check= restTemplate.getForObject("http://localhost:8081/api/v1/user-service/artists/check/"+artistId,boolean.class);
        if(check){
            List<Publication> publications =publicationRepository.findByArtistId(artistId);
            for (Publication publication : publications){
                Artist artist= restTemplate.getForObject("http://localhost:8081/api/v1/user-service/artists/"+ publication.getArtistId(),Artist.class);
                publication.setArtist(artist);
            }
            return publications;
        }
        else {
            throw  new ResourcePerzonalized("Artist id was not found");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long publicationId) {
        return publicationRepository.findById(publicationId).map(post -> {
            publicationRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, publicationId));
    }


    @Override
    public boolean existsPublication(Long publicationId) {
        return publicationRepository.existsById(publicationId);
    }
}
