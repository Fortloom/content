package com.fortlom.content.domain.ContentAgrregate.valueobject;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Artist {
    private Long id;

    private String username;

    private String realname;

    private String lastname;

    private String email;

    private String password;
    private Long artistfollowers;

    private String instagramLink;

    private String facebookLink;

    private String twitterLink;
}
