package com.fortlom.content.domain.ContentAgrregate.entity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@With
@AllArgsConstructor
@Table(name="events")
//@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date releasedDate;

    @NotNull
    @NotBlank
    private String ticketLink;

    @NotNull
    private Long publicationId;

    @Transient
    private Publication publication;

    //@NotNull
    //@NotBlank
    //@Size(max = 200)
    //private String name;
}
