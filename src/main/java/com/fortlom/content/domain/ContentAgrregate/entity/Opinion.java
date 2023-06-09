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
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Opinion extends Publication {
    private Boolean isAgree;

    @NotNull
    private Long publicationId;


}
