package com.huykun.ecommercebe.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.huykun.ecommercebe.constant.ResponseStatusConstant;
import com.huykun.ecommercebe.response.ResponseDTO;

@RestControllerAdvice
public class ExceptionHandlers extends RuntimeException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> badRequestException(BadRequestException exception) {
        ResponseDTO dto = new ResponseDTO();
        dto.setMessage(exception.getMessage());
        dto.setStatus(ResponseStatusConstant.FAILURE);
        return ResponseEntity.badRequest().body(dto);
    }

    // @ExceptionHandler(value = { BadRequestException.class, AuthenticationException.class })
    // public ResponseEntity<Object> usernameOrPasswordNotFound(AuthenticationException exception) {
    //     ResponseDTO dto = new ResponseDTO();
    //     dto.setMessage(AuthErrorMessage.INVALID_EMAIL_PASSWORD);
    //     dto.setStatus(ResponseStatusConstant.FAILURE);
    //     return ResponseEntity.badRequest().body(dto);
    // }
}