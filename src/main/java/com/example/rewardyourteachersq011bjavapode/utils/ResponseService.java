package com.example.rewardyourteachersq011bjavapode.utils;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public  class ResponseService<T>{

    private T response;
    private HttpStatus status;

    public  ResponseEntity<T> response(T response , HttpStatus status){
        return new ResponseEntity<>(response , status);
    }

}
