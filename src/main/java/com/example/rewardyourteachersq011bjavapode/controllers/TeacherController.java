package com.example.rewardyourteachersq011bjavapode.controllers;



import com.example.rewardyourteachersq011bjavapode.config.Security.CustomUserDetails;
import com.example.rewardyourteachersq011bjavapode.dto.TeacherDetails;
import com.example.rewardyourteachersq011bjavapode.dto.TeacherEditProfileDto;
import com.example.rewardyourteachersq011bjavapode.dto.TeacherRegistrationDto;
import com.example.rewardyourteachersq011bjavapode.models.User;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.response.UserRegistrationResponse;
import com.example.rewardyourteachersq011bjavapode.service.CurrentUser;
import com.example.rewardyourteachersq011bjavapode.service.ITeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import com.example.rewardyourteachersq011bjavapode.service.UserService;
import com.example.rewardyourteachersq011bjavapode.utils.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class TeacherController {
    private final UserService userService;
    private final ResponseService<ApiResponse<List<User>>> responseService;
    private final ITeacherService teacherService;

    @GetMapping("/getAllWithPagination/{pageNo}/{pageSize}/{schoolName}")
    public Page<TeacherDetails> getAllTeachersBySchoolWithPagination(@PathVariable("pageNo") int pageNo,
                                                                     @PathVariable("pageSize") int pageSize,
                                                                     @PathVariable("schoolName") String schoolName) {
        return teacherService.getAllTeacherBySchoolWithPagination(pageNo, pageSize, schoolName);
    }

    @GetMapping("getAllWithPagination")
    public Page<TeacherDetails> getAllTeachersWithPagination(@RequestParam int pageNo,
                                                             @RequestParam int pageSize) {

        return teacherService.getAllTeachersWithPagination(pageNo, pageSize);
    }


    @GetMapping("/search/{name}")
    public ResponseEntity<ApiResponse<List<User>>> searchTeacher(@PathVariable(value = "name") String name) {
        return responseService.response(userService.searchTeacher(name), HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewTeacherProfile(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(userService.viewProfile(id), HttpStatus.OK);
    }

    @PutMapping(value="/edit-teacherProfile")
    public ResponseEntity<ApiResponse<String>> editTeachProfile(@CurrentUser CustomUserDetails currentUser, @RequestBody TeacherEditProfileDto userDto) {
        log.info("successfully updated");
        return new ResponseEntity<>(teacherService.editTeacherProfile(currentUser, userDto), HttpStatus.OK);
    }

    @PostMapping(value = "/register-teacher")
    public ResponseEntity<UserRegistrationResponse> registerTeacher(TeacherRegistrationDto teacherDto, @RequestPart MultipartFile teacherIdImage) throws IOException {
        log.info("Successfully Registered {} ", teacherDto.getEmail());
        return new ResponseEntity<>(teacherService.registerTeacher(teacherDto, teacherIdImage), HttpStatus.CREATED);
    }
}
