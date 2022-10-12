package com.example.rewardyourteachersq011bjavapode.service;

import com.example.rewardyourteachersq011bjavapode.config.Security.CustomUserDetails;
import com.example.rewardyourteachersq011bjavapode.dto.UserDto;
import com.example.rewardyourteachersq011bjavapode.dto.UserEditProfileDto;
import com.example.rewardyourteachersq011bjavapode.dto.UserProfileDto;
import com.example.rewardyourteachersq011bjavapode.models.User;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.response.UserRegistrationResponse;

import java.math.BigDecimal;
import java.util.List;



public interface UserService {

    ApiResponse<String> logoutUser(CustomUserDetails currentUser, String bearerToken);


    BigDecimal currentBalance();


    ApiResponse<List<User>> searchTeacher(String name);

    User findById(Long id);

    ApiResponse<UserProfileDto> viewProfile(Long id);
    User findUserById(Long id);

    ApiResponse<String> editUserProfile(CustomUserDetails currentUser, UserEditProfileDto userEditProfileDto);
    UserRegistrationResponse registerUser(UserDto userDto);


}
