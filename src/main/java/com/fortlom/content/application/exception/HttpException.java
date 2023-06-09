package com.fortlom.content.application.exception;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class HttpException  extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private HttpStatus state;
    private String message;


    public HttpException(HttpStatus state, String message) {
        super();
        this.state = state;
        this.message = message;
    }
    public HttpException(HttpStatus estado, String mensaje,String mensaje1) {
        super();
        this.state = estado;
        this.message = mensaje;
        this.message=mensaje1;
    }
}
