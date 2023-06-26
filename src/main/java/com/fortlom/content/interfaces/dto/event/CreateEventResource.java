package com.fortlom.content.interfaces.dto.event;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateEventResource {
    private String name;
    private String description;
    private Date releasedDate;
    private String ticketLink;
    private Long publicationId;
}
