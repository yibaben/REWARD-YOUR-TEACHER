package com.example.rewardyourteachersq011bjavapode.serviceImpl;

import com.example.rewardyourteachersq011bjavapode.enums.NotificationType;
import com.example.rewardyourteachersq011bjavapode.enums.Role;
import com.example.rewardyourteachersq011bjavapode.models.Message;
import com.example.rewardyourteachersq011bjavapode.models.Notification;
import com.example.rewardyourteachersq011bjavapode.models.Transaction;
import com.example.rewardyourteachersq011bjavapode.models.User;
import com.example.rewardyourteachersq011bjavapode.repository.NotificationRepository;
import com.example.rewardyourteachersq011bjavapode.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class NotificationServiceImplTest {
    @Mock
    NotificationRepository notificationRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    NotificationServiceImpl notificationService;
    private User user;
    private Notification notification;
    List<Transaction> transactionList;
    List<Message> messageList;
    List<Notification> notificationList;


    @BeforeEach
    void setUp() {
        LocalDateTime localDateTime = LocalDateTime.of(2022, Month.SEPTEMBER, 20,4,12);
        user = new User(1l,localDateTime,localDateTime,"oluwaseun", Role.TEACHER,"oluwaseun@gmail.com","oluwaseun","","",transactionList,messageList,notificationList,"school");
    }

    @Test
    void saveNotification() {
        when(userRepository.findById(1l)).thenReturn(Optional.ofNullable(user));
        notification = new Notification("you have been credited", NotificationType.CREDIT_NOTIFICATION,user);
        when(notificationRepository.save(notification)).thenReturn(notification);
        var actual = notificationService.saveNotification(1l,"you have been credited", NotificationType.CREDIT_NOTIFICATION);
        assertNotNull(notificationRepository.save(notification));
    }
}