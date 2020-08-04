package com.vyara.fantasy.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Entity Already Exists")
public class EntityAlreadyExistsException extends RuntimeException{

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
};
