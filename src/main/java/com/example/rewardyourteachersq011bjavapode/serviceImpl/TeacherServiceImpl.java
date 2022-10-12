package com.example.rewardyourteachersq011bjavapode.serviceImpl;

import com.example.rewardyourteachersq011bjavapode.config.Security.CustomUserDetails;
import com.example.rewardyourteachersq011bjavapode.dto.TeacherDetails;
import com.example.rewardyourteachersq011bjavapode.dto.TeacherEditProfileDto;
import com.example.rewardyourteachersq011bjavapode.dto.TeacherRegistrationDto;
import com.example.rewardyourteachersq011bjavapode.exceptions.UserAlreadyExistException;
import com.example.rewardyourteachersq011bjavapode.exceptions.UserNotFoundException;
import com.example.rewardyourteachersq011bjavapode.models.Subject;
import com.example.rewardyourteachersq011bjavapode.models.Teacher;
import com.example.rewardyourteachersq011bjavapode.models.User;
import com.example.rewardyourteachersq011bjavapode.models.Wallet;
import com.example.rewardyourteachersq011bjavapode.repository.SubjectRepository;
import com.example.rewardyourteachersq011bjavapode.repository.TeacherRepository;
import com.example.rewardyourteachersq011bjavapode.repository.UserRepository;
import com.example.rewardyourteachersq011bjavapode.repository.WalletRepository;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.response.UserRegistrationResponse;
import com.example.rewardyourteachersq011bjavapode.service.ITeacherService;
import com.example.rewardyourteachersq011bjavapode.utils.UserUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.rewardyourteachersq011bjavapode.enums.Role.TEACHER;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements ITeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final WalletRepository walletRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserUtil userUtil;



    @Override
    public Page<TeacherDetails> getAllTeacherBySchoolWithPagination(int pageNo, int pageSize, String schoolName) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<TeacherDetails> teacherDetailsList = teacherRepository.findAllBySchool(schoolName, pageable).stream().map(teacher -> new TeacherDetails(teacher.getName(), teacher.getSchool(), teacher.getTeachingPeriod())).collect(Collectors.toList());
        return new PageImpl<>(teacherDetailsList, pageable, teacherDetailsList.size());
    }

    @Override
    public Page<TeacherDetails> getAllTeachersWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<TeacherDetails> teacherDetailsList = teacherRepository.findAll(pageable).stream().map(teacher -> new TeacherDetails(teacher.getName(), teacher.getSchool(), teacher.getTeachingPeriod())).collect(Collectors.toList());
        return new PageImpl<>(teacherDetailsList, pageable, teacherDetailsList.size());
    }

    @Override
    public ApiResponse<String> editTeacherProfile(CustomUserDetails currentUser, TeacherEditProfileDto teacherEditProfileDto)  {
        Teacher teacher =  teacherRepository.findByEmail(currentUser.getUsername()).orElseThrow(() -> new UserNotFoundException("teacher's details not found"));
        teacher.setName(teacherEditProfileDto.getName());
        teacher.setSchool(teacherEditProfileDto.getSchool());
        teacher.setTeachingPeriod(teacherEditProfileDto.getTeachingPeriod());
        teacher.setSchoolType(teacherEditProfileDto.getSchoolType());
        teacherRepository.save(teacher);
        String response = teacherEditProfileDto.getName() + " profile updated successfully";
        return new ApiResponse<>("success", LocalDateTime.now(), response);
    }

    @Override
    public UserRegistrationResponse registerTeacher(TeacherRegistrationDto teacherDto, MultipartFile teacherId) throws IOException {
        String email = teacherDto.getEmail();
        Optional<User> existingUser = userRepository.findUserByEmail(email);

        if (existingUser.isEmpty()) {
            Teacher teacher = new Teacher();
            teacher.setName(teacherDto.getName());
            teacher.setEmail(teacherDto.getEmail());
            teacher.setPassword(passwordEncoder.encode(teacherDto.getPassword()));
            teacher.setSchool(teacherDto.getSchool());
            teacher.setTeachingPeriod(teacherDto.getTeachingPeriod());
            teacher.setSchoolType(teacherDto.getSchoolType());
            teacher.setRole(TEACHER);
            teacher.setTeacherIdUrl(userUtil.uploadImage(teacherId));
            userRepository.save(teacher);
            Wallet userWallet = new Wallet(new BigDecimal("0"), teacher);
            walletRepository.save(userWallet);


            teacherDto.getSubjectList().forEach(subject -> {
                subjectRepository.save(new Subject(subject, teacher));
            });

            return new UserRegistrationResponse("success", LocalDateTime.now());
        } else {
            throw new UserAlreadyExistException("User already exist");
        }

    }

}

