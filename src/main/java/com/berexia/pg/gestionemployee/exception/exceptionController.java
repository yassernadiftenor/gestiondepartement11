package com.berexia.pg.gestionemployee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class exceptionController {
    @ExceptionHandler({notFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(notFoundException e) {
        error Error=new error(e.getMsg(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<Object>(Error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRunTimeException(RuntimeException e) {
        error Error=new error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,LocalDateTime.now());
        return new ResponseEntity<Object>(Error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<Object> handleNPException(NullPointerException e) {
        error Error=new error(e.getMessage(), HttpStatus.BAD_REQUEST,LocalDateTime.now());
        return new ResponseEntity<Object>(Error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegaleArgException(IllegalArgumentException e) {
        error Error=new error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,LocalDateTime.now());
        return new ResponseEntity<Object>(Error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({emptyFields.class})
    public ResponseEntity<Object> handleEmptyFieldsException(emptyFields e) {
        error Error=new error(e.getMsg(), HttpStatus.BAD_REQUEST,LocalDateTime.now());
        return new ResponseEntity<Object>(Error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({alreadyExistException.class})
    public ResponseEntity<Object> hundlerAlreadyExistException(alreadyExistException e) {
        error Error=new error(e.getMsg(), HttpStatus.BAD_REQUEST,LocalDateTime.now());
        return new ResponseEntity<Object>(Error, HttpStatus.BAD_REQUEST);
    }
}
