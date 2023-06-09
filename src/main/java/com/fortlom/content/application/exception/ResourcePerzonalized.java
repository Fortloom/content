package com.fortlom.content.application.exception;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourcePerzonalized extends RuntimeException{


    public ResourcePerzonalized(String message) {

        super(message);


    }



}
