package com.example.rewardyourteachersq011bjavapode.controllers;

import com.example.rewardyourteachersq011bjavapode.response.ExceptionResponse;
import com.example.rewardyourteachersq011bjavapode.exceptions.ResourceNotFoundException;
import com.example.rewardyourteachersq011bjavapode.utils.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.rewardyourteachersq011bjavapode.exceptions.*;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    private final ResponseService<ExceptionResponse> responseService;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFoundHandler(ResourceNotFoundException exception){
        return responseService.response(new ExceptionResponse(exception.getMessage(), LocalDateTime.now() , HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTokenRequestException.class)
    public ResponseEntity<ExceptionResponse> InvalidTokenHandler(InvalidTokenRequestException exception){
        return responseService.response(new ExceptionResponse(exception.getMessage(), LocalDateTime.now() , HttpStatus.NOT_ACCEPTABLE), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> userAlreadyExistHandler(UserAlreadyExistException existException){
        return responseService.response(new ExceptionResponse(existException.getMessage(),LocalDateTime.now(),HttpStatus.CONFLICT),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundHandler(UserNotFoundException exception){
        return responseService.response(new ExceptionResponse(exception.getMessage(),LocalDateTime.now(),HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }


}
