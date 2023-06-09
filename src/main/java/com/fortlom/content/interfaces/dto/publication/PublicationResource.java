package com.fortlom.content.interfaces.dto.publication;
import com.fortlom.content.domain.ContentAgrregate.valueobject.Artist;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PublicationResource {
    private Long id;


    private String description;



    private boolean image;


    private Date registerdate;



    private Long artistid;
    private Artist artist;
}
