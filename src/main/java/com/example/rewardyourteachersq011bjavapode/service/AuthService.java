package com.example.rewardyourteachersq011bjavapode.service;


import com.example.rewardyourteachersq011bjavapode.dto.LoginDTO;
import com.example.rewardyourteachersq011bjavapode.dto.PrincipalDto;
import com.example.rewardyourteachersq011bjavapode.dto.TeacherRegistrationDto;
import com.example.rewardyourteachersq011bjavapode.dto.UserDto;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.response.UserRegistrationResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AuthService {

    ApiResponse<PrincipalDto> loginUser(LoginDTO loginDTO);
}
