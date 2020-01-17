package com.berexia.pg.gestionemployee.exception;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public class alreadyExistException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String msg;
    public alreadyExistException(String Msg) {
        this.msg=Msg;
        log.error(this.msg,this);
    }
}
