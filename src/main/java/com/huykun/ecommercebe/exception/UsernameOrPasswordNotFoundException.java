package com.huykun.ecommercebe.exception;

public class UsernameOrPasswordNotFoundException extends RuntimeException{
    public UsernameOrPasswordNotFoundException(String message) {
        super(message);
    }
}
