package com.fortlom.content.domain.ContentAgrregate.entity;

import com.fortlom.content.domain.ContentAgrregate.valueobject.Artist;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
@With
@NoArgsConstructor
@AllArgsConstructor
@Table(name="publications")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String description;

    //private Boolean image;

    @Temporal(TemporalType.DATE)
    private Date registerDate;

    @NotNull
    private Long artistId;

    //TODO: Implement
    //private Long eventId;

    @Transient
    private Artist artist;

}
