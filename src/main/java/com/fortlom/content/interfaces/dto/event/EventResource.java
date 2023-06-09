package com.fortlom.content.interfaces.dto.event;

import com.fortlom.content.domain.ContentAgrregate.valueobject.Artist;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Setter
@Getter
public class EventResource {

    private Long id;


    private String description;


    private Date registerdate;



    private Long artistid;

    private String name;


    private String ticketLink;


    private Date releasedDate;

    private Artist artist;
}
