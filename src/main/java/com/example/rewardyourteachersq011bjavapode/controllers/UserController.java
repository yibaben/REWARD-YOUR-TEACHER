package com.example.rewardyourteachersq011bjavapode.controllers;

import com.example.rewardyourteachersq011bjavapode.config.Security.CustomUserDetails;

import com.example.rewardyourteachersq011bjavapode.dto.UserDto;
import com.example.rewardyourteachersq011bjavapode.dto.UserEditProfileDto;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.response.UserRegistrationResponse;

import com.example.rewardyourteachersq011bjavapode.service.CurrentUser;
import com.example.rewardyourteachersq011bjavapode.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.CREATED;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser(@CurrentUser CustomUserDetails currentUser, @RequestHeader("Authorization") String bearToken) {
        return new ResponseEntity<>(userService.logoutUser(currentUser, bearToken), OK);
    }



    @GetMapping("/wallet")
    public ResponseEntity<?> currentUserBalance(){
        return new ResponseEntity<>(userService.currentBalance(),OK);
    }

    @PutMapping(value="/edit-userprofile")
    public ResponseEntity<ApiResponse<String>> editProfile(@CurrentUser CustomUserDetails currentUser, @RequestBody UserEditProfileDto userDto){
        log.info("successfully updated");
        return new ResponseEntity<>(userService.editUserProfile(currentUser, userDto), HttpStatus.OK);
    }

    @PostMapping(value = "/register-student")
    public ResponseEntity<UserRegistrationResponse> registerUser(@RequestBody UserDto userDto) {
        log.info("Successfully Registered {} ", userDto.getEmail());
        return new ResponseEntity<>(userService.registerUser(userDto), CREATED);
    }

}