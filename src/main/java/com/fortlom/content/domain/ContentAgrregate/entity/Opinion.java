package com.fortlom.content.domain.ContentAgrregate.entity;


import javax.persistence.PrimaryKeyJoinColumn;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@Entity
@With
@AllArgsConstructor
@Table(name="opinions")
//@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isAgree;

    @NotNull
    private Long fanaticId;

    private Long publicationId;
    @Transient
    private Publication publication;

    private Long eventId;
    @Transient
    private Event event;

}
