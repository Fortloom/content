package com.fortlom.content.application.service;
import com.fortlom.content.application.exception.ResourceNotFoundException;
import com.fortlom.content.application.exception.ResourcePerzonalized;


import com.fortlom.content.domain.ContentAgrregate.entity.Opinion;
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
    public List<Opinion> getAll() {


        List<Opinion> opinions =publicationRepository.findAll();
        for (Opinion opinion : opinions){
            Artist artist= restTemplate.getForObject("https://fortlom-account.herokuapp.com/api/v1/userservice/artists/"+ opinion.getArtistid(),Artist.class);
            opinion.setArtist(artist);
        }
        return opinions;


    }

    @Override
    public Page<Opinion> getAll(Pageable pageable) {


        Page<Opinion>publications=publicationRepository.findAll(pageable);
        for (Opinion opinion :publications){
            Artist artist= restTemplate.getForObject("https://fortlom-account.herokuapp.com/api/v1/userservice/artists/"+ opinion.getArtistid(),Artist.class);
            opinion.setArtist(artist);
        }



        return publications;


    }

    @Override
    public Opinion getById(Long publicationId) {

        Opinion opinion =publicationRepository.findById(publicationId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, publicationId));
        Artist artist= restTemplate.getForObject("https://fortlom-account.herokuapp.com/api/v1/userservice/artists/"+ opinion.getArtistid(),Artist.class);
        opinion.setArtist(artist);
        return opinion;
    }

    @Override
    public Opinion create(Long artistId, Opinion request, String type) {
        boolean check= restTemplate.getForObject("https://fortlom-account.herokuapp.com/api/v1/userservice/artists/check/"+artistId,boolean.class);
        if(check){

            Date date = new Date();
            request.setArtistid(artistId);
            request.setRegisterdate(date);
            if(type.equals("true")){
                request.setImage(true);
            }else {
                request.setImage(false);
            }


            return publicationRepository.save(request);
        }else {
            throw  new ResourcePerzonalized("id inexistente");
        }
    }

    @Override
    public Opinion update(Long publicationId, Opinion request) {
        return publicationRepository.findById(publicationId).map(post->{


            publicationRepository.save(post);
            return post;

        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, publicationId));
    }

    @Override
    public List<Opinion> getPublicationByArtistId(Long artistId) {
        boolean check= restTemplate.getForObject("https://fortlom-account.herokuapp.com/api/v1/userservice/artists/check/"+artistId,boolean.class);
        if(check){
            List<Opinion> opinions =publicationRepository.findByArtistid(artistId);
            for (Opinion opinion : opinions){
                Artist artist= restTemplate.getForObject("https://fortlom-account.herokuapp.com/api/v1/userservice/artists/"+ opinion.getArtistid(),Artist.class);
                opinion.setArtist(artist);
            }
            return opinions;}
        else {
            throw  new ResourcePerzonalized("id inexistente");


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
    public boolean existspublication(Long publicationId) {
        return publicationRepository.existsById(publicationId);
    }
}
