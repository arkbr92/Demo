package com.diamler.demo.exception;

import java.time.LocalDateTime;

/**
 * Exception Response
 * @author arunkbr
 *
 */

public class ExceptionResponse {

    private String message;
    private LocalDateTime dateTime;
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }    
}