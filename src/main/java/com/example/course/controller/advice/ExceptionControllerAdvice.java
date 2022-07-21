package com.example.course.controller.advice;

import com.example.course.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

import static com.example.course.controller.advice.ErrorType.UNEXPECTED_ERROR;
import static com.example.course.controller.advice.ErrorType.UNKNOWN_DATA_ITEM;

@Slf4j
@Order
@ControllerAdvice
public class ExceptionControllerAdvice
{
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception)
    {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(HttpStatus.NOT_FOUND.toString())
                .errorKey(UNKNOWN_DATA_ITEM.name())
                .errorMessage(exception.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception) {
        String messageId = UUID.randomUUID().toString();
        String errorDescription = "Unexpected system exception ID: " + messageId;

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .errorKey(UNEXPECTED_ERROR.name())
                .errorMessage(errorDescription).build();
        log.warn(errorDescription, exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}