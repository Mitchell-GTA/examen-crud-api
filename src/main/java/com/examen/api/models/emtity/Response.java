package com.examen.api.models.emtity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String message;

    private Object data;

    private String error;

    private HttpStatus status;

    public Response(String message, Object data, HttpStatus status) {
        this.message = message;
        this.data = data;
        this.status = status;
        this.error = null;
    }
    public Response(String message, String error, HttpStatus status) {
        this.message = message;
        this.error = error;
        this.status = status;
    }
}
