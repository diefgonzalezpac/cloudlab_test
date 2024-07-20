package com.test.superheroes.exception;


import com.test.superheroes.exception.model.ApiExceptionResponse;
import com.test.superheroes.exception.model.Problem;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.stream.Collectors;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> handleConflict(Exception exception, HttpServletRequest request) {
        Problem problem = new Problem("Error genérico", exception.getMessage(), request.getRequestURI());
        return this.registerException(exception, HttpStatus.INTERNAL_SERVER_ERROR, problem, exception.getClass().toString());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionResponse> apiExceptionHandler(ApiException exception) {
        return this.registerException(exception, exception.getStatus(), exception.getProblem(), exception.getClass().toString());
    }

    private ResponseEntity<ApiExceptionResponse> registerException(Exception exception, HttpStatus httpStatus, Problem problem, String type) {
        long ownId = System.currentTimeMillis();
        HashMap<String, Object> hashMap = new HashMap<>();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);

        hashMap.put("OwnID", ownId);
        hashMap.put("Exception", problem.getTitle());
        hashMap.put("StackTrace", sw.toString());

        log.error(hashMap.keySet().stream()
                .map(key -> key + "=" + hashMap.get(key))
                .collect(Collectors.joining(", ", "{", "}")));

        ApiExceptionResponse error = new ApiExceptionResponse(httpStatus, problem);

        return new ResponseEntity<>(error, httpStatus);
    }
}
