package com.coralsoft.domorder.exceptions.exceptionHandler;

import com.coralsoft.domorder.exceptions.OrderNotFoundException;
import com.coralsoft.domorder.exceptions.OrderStatusConflictException;
import com.coralsoft.domorder.exceptions.ProductNotFoundException;
import com.coralsoft.domorder.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class DomOrderExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request){
        ProblemType problemType = ProblemType.ORDER_NOT_FOUND;
        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();
        Problem problem = buildProblem(status, problemType, detail);
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request){
        ProblemType problemType = ProblemType.PRODUCT_NOT_FOUND;
        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();
        Problem problem = buildProblem(status, problemType, detail);
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
        ProblemType problemType = ProblemType.USER_NOT_FOUND;
        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();
        Problem problem = buildProblem(status, problemType, detail);
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(OrderStatusConflictException.class)
    public ResponseEntity<Object> handleOrderStatusConflictException(OrderStatusConflictException ex, WebRequest request){
        ProblemType problemType = ProblemType.ORDERSTATUS_CONFLICT;
        HttpStatus status = HttpStatus.CONFLICT;
        String detail = ex.getMessage();
        Problem problem = buildProblem(status, problemType, detail);
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    public Problem buildProblem(HttpStatus status,ProblemType problemType, String detail) {
        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setTimestamp(LocalDateTime.now(ZoneId.of("UTC")));
        problem.setType(problemType.getUri());
        problem.setTitle(problemType.getTitle());
        problem.setDetail(detail);
        return problem;
    }
}
