package com.example.rewardyourteachersq011bjavapode.serviceImpl;
import com.example.rewardyourteachersq011bjavapode.dto.TeacherRegistrationDto;
import com.example.rewardyourteachersq011bjavapode.dto.UserDto;
import com.example.rewardyourteachersq011bjavapode.dto.UserProfileDto;
import com.example.rewardyourteachersq011bjavapode.enums.NotificationType;
import com.example.rewardyourteachersq011bjavapode.enums.Role;
import com.example.rewardyourteachersq011bjavapode.enums.SchoolType;
import com.example.rewardyourteachersq011bjavapode.enums.Status;
import com.example.rewardyourteachersq011bjavapode.exceptions.UserAlreadyExistException;
import com.example.rewardyourteachersq011bjavapode.models.*;
import com.example.rewardyourteachersq011bjavapode.repository.TeacherRepository;
import com.example.rewardyourteachersq011bjavapode.repository.UserRepository;
import com.example.rewardyourteachersq011bjavapode.repository.WalletRepository;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.response.UserRegistrationResponse;
import com.example.rewardyourteachersq011bjavapode.service.ITeacherService;
import com.example.rewardyourteachersq011bjavapode.utils.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    @Mock
    WalletRepository walletRepository;

    @InjectMocks
    UserServiceImpl userService;

    @InjectMocks
    ITeacherService teacherService;

    @Mock
    TeacherRepository teacherRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    UserUtil userUtil;


    @InjectMocks
    AuthServiceImpl authService;
    private LocalDateTime localDateTime;
    private User user;
    private Teacher teacher;
    List<Subject> subjectList = new ArrayList<>();
    List<Transaction> transactionList = new ArrayList<>();
    List<Message> messageList = new ArrayList<>();
    List<Notification> notificationList;
    private ApiResponse response;
     List<User> userList;
    private List<String> listSubject;
    private Wallet wallet;
    private MultipartFile multipartFile;
    private Subject subject;
    private Notification notification;
    private Message message;

    private MultipartFile teacherId;




    @BeforeEach
    void setUp() {
        localDateTime = LocalDateTime.now();
        user = new User(1L , localDateTime , localDateTime , "chioma", Role.TEACHER,"chioma@gmail.com","1234","","",transactionList, messageList, notificationList, "school");
        teacher = new Teacher( "chioma",Role.TEACHER,"chioma@gmail.com","1234", "","",transactionList, messageList, notificationList, "school","20", Status.INSERVICE,"", SchoolType.SECONDARY,"oxy.png",subjectList);
       // message = new Message("success", user);
        when(userRepository.save(user)).thenReturn(user);
        message = new Message("new message", user);
        notification = new Notification("alertz", NotificationType.CREDIT_NOTIFICATION, user);
        subject = new Subject("Economics", teacher);
        wallet = new Wallet(new BigDecimal(100), user);
        listSubject = new ArrayList<>();
        listSubject.add("Math");
    }

    @Test
    void searchTeacher() {
        when(userRepository.findByRoleAndNameContainingIgnoreCase(Role.TEACHER, "chioma")).thenReturn(userList);
        var actual = userService.searchTeacher("chioma");
        actual.setTimeStamp(localDateTime);
        response = new ApiResponse<>("success", localDateTime, userList);
        assertEquals(response,actual);

    }

    @Test
    void viewProfile() {
        UserProfileDto profileDto = new UserProfileDto("","chioma","school","chioma@gmail.com","","");
        when(teacherRepository.findById(1L)).thenReturn(Optional.ofNullable(teacher));
        var actual = userService.viewProfile(1l);
        actual.setTimeStamp(localDateTime);
         response = new ApiResponse("success", localDateTime,profileDto);
       assertEquals(response, actual);

    }
    @Test
    void registerUser() {
        UserDto userDto = new UserDto("chioma", "chioma@gmail.com", passwordEncoder.encode("1234"), "school");
        when(userRepository.findUserByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        var actual = userService.registerUser(userDto);
        assertEquals("success", actual.getMessage());

    }

    @Test
    void registerAlreadyExistingUser() {
        UserDto userDto = new UserDto("chioma", "chioma@gmail.com", passwordEncoder.encode("1234"), "school");
        when(userRepository.findUserByEmail(userDto.getEmail())).thenReturn(Optional.of(user));
        assertThrows(UserAlreadyExistException.class, () -> userService.registerUser(userDto));

    }
    @Test
    void registerTeacher() throws IOException {
        multipartFile = mock(MultipartFile.class);
        TeacherRegistrationDto teacherDto = new TeacherRegistrationDto("2012-2016", listSubject, SchoolType.SECONDARY);
        when(userRepository.findUserByEmail(teacherDto.getEmail())).thenReturn(Optional.empty());
        when(userUtil.uploadImage(multipartFile)).thenReturn("uploaded");
        var actual = teacherService.registerTeacher(teacherDto, multipartFile);
        assertEquals("success", actual.getMessage());
    }
    @Test
    void currentUserWalletBalance() {
        when(userUtil.getAuthenticatedUserEmail()).thenReturn(user.getEmail());
        when(walletRepository.findWalletByUserEmail(user.getEmail())).thenReturn(Optional.of(wallet));
        var actual = userService.currentBalance();
        assertEquals(new BigDecimal(100), actual);
    }



}