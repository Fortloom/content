package com.fortlom.content.interfaces.dto.opinion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpinionResource {
    private Long id;
    private Boolean isAgree;
    private Long fanaticId;
    private Long eventId;
    private Long publicationId;
}
