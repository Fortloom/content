package com.fortlom.content.interfaces.dto.event;

import com.fortlom.content.domain.ContentAgrregate.entity.Publication;
import com.fortlom.content.domain.ContentAgrregate.valueobject.Artist;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Setter
@Getter
public class EventResource {
    private Long id;
    private String name;
    private String description;
    private Date releasedDate;
    private String ticketLink;
    private String publicationId;
    private Publication publication;
    //private Date registerdate;
    //private Long artistid;
    //private Artist artist;
}
