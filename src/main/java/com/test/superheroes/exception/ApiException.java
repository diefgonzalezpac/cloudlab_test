package com.test.superheroes.exception;

import com.test.superheroes.exception.model.Problem;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private Problem problem;
    private HttpStatus status;
    public ApiException(Problem problem, int status) {
        this.problem = problem;
        this.status = HttpStatus.resolve(status);
    }
}