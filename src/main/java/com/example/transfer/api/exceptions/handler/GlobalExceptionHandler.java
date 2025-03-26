package com.example.transfer.api.exceptions.handler;

import com.example.transfer.api.exceptions.*;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundPersonException.class)
    public ResponseEntity<String> handleNotFoundPersonException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonNotAllowedToMakeTransfersException.class)
    public ResponseEntity<String> handlePersonNotAllowedToMakeTransfersException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({InsufficientBalanceException.class, InvalidPersonIdException.class})
    public ResponseEntity<String> handleInsufficientBalanceException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedTransferException.class)
    public ResponseEntity<String> handleUnauthorizedTransferException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(Exception e) {
        final String message = "Erro ao tentar acessar o banco de dados";
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, HttpMessageNotWritableException.class})
    public ResponseEntity<String> handleNotReadableException(Exception e) {
        final String message = "Erro ao tentar ler ou escrever o corpo da requisição";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder("Validação falhou para os campos: ");
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            message.append(String.format("[%s: %s] ", error.getField(), error.getDefaultMessage()));
        }
        return new ResponseEntity<>(message.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        final String message = "Ocorreu um erro inesperado";
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
