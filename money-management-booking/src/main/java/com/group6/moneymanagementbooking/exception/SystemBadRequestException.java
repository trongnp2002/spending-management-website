package com.group6.moneymanagementbooking.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SystemBadRequestException extends RuntimeException {

    // @Serial
    // private static final long serialVersionUID = 1372210541405289235L;

    public SystemBadRequestException(String message) {
        super(message);
    }
}