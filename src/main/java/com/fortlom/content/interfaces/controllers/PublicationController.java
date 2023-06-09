package com.fortlom.content.interfaces.controllers;
import com.fortlom.content.domain.ContentAgrregate.entity.Opinion;
import com.fortlom.content.domain.ContentAgrregate.service.PublicationService;
import com.fortlom.content.interfaces.dto.publication.CreatePublicationResource;
import com.fortlom.content.interfaces.dto.publication.PublicationResource;
import com.fortlom.content.interfaces.mapping.entity.PublicationMapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/contentservice")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;

    @Autowired
    private PublicationMapper mapper;

    @Autowired
    private ModelMapper mapping;

    @GetMapping("/publications")
    public Page<PublicationResource> getAllPublications(Pageable pageable) {
        return mapper.modelListToPage(publicationService.getAll(), pageable);
    }

    @GetMapping("/publications/{publicationId}")
    public PublicationResource getPublicationById(@PathVariable("publicationId") Long publicationId) {
        return mapper.toResource(publicationService.getById(publicationId));
    }
    @PostMapping("/artists/{artistId}/type/{type}/publications")
    public ResponseEntity<PublicationResource> createPublication(@PathVariable Long artistId,@PathVariable String type,@RequestBody CreatePublicationResource request) {
        Opinion opinion = mapping.map(request, Opinion.class);
        return ResponseEntity.ok(mapping.map(publicationService.create(artistId, opinion,type), PublicationResource.class));
    }
    @DeleteMapping("/publications/{publicationId}")
    public ResponseEntity<?> deletePublication(@PathVariable Long publicationId) {
        return publicationService.delete(publicationId);
    }
    @GetMapping("/artists/{artistId}/publications")
    public ResponseEntity<Page<PublicationResource>> getAllPublicationByArtistId(@PathVariable Long artistId,Pageable pageable) {

        return ResponseEntity.ok(mapper.modelListToPage(publicationService.getPublicationByArtistId(artistId), pageable));
    }
    @GetMapping("publications/check/{publicationId}")
    public boolean existspublication(@PathVariable("publicationId") Long publicationId){
        return publicationService.existspublication(publicationId);
    }
}
