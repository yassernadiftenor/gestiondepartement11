package com.berexia.pg.gestionemployee.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class error {
    private String msg;
    private HttpStatus httpStatus;
    private LocalDateTime dateTime;
}
