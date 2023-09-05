package com.coralsoft.domproduct.exceptions.exceptionHandler;

import com.coralsoft.domproduct.exceptions.BrandNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@ControllerAdvice
public class DomProductExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<Object> handleBrandNotFoundException(EntityNotFoundException ex, WebRequest request){
        ProblemType problemType = ProblemType.BRAND_NOT_FOUND;
        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();
        Problem problem = buildProblem(status, problemType, detail);
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    public Problem buildProblem(HttpStatus status,ProblemType problemType, String detail) {
        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setTimestamp(LocalDateTime.now());
        problem.setType(problemType.getUri());
        problem.setTitle(problemType.getTitle());
        problem.setDetail(detail);
        return problem;
    }
}
