package com.example.cardservice.handler;

import com.example.cardservice.exception.CardIdException;
import com.example.cardservice.exception.CardReissueException;
import com.example.cardservice.exception.CardStatusException;
import com.example.cardservice.exception.InactiveCardException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CardReissueException.class)
    public ResponseEntity<?>handleCardReissueEx(CardReissueException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(InactiveCardException.class)
    public ResponseEntity<?> inactiveCardEx(InactiveCardException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(CardStatusException.class)
    public ResponseEntity<?> cardStatusEx(CardStatusException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(CardIdException.class)
    public ResponseEntity<?> cardIdEx(CardIdException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
