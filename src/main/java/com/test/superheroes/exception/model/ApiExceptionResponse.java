package com.test.superheroes.exception.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ApiExceptionResponse {
    private HttpStatus status;
    private Date timestamp;
    private String title;
    private String message;
    private String detail;

    private ApiExceptionResponse() {
        this.timestamp = new Date();
    }

    public ApiExceptionResponse(HttpStatus status, Problem problem) {
        this();
        this.status = status;
        this.title = problem.getTitle();
        this.message = problem.getMessage();
        this.detail = problem.getDetail();
    }


}