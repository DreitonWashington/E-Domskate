package com.coralsoft.domproduct.exceptions.exceptionHandler;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Problem {
    private Integer status;
    private LocalDateTime timestamp;
    private String type;
    private String title;
    private String detail;
}
