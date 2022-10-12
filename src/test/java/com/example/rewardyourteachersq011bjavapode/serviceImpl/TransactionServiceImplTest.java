package com.example.rewardyourteachersq011bjavapode.serviceImpl;

import com.example.rewardyourteachersq011bjavapode.enums.Role;
import com.example.rewardyourteachersq011bjavapode.enums.TransactionType;
import com.example.rewardyourteachersq011bjavapode.models.*;
import com.example.rewardyourteachersq011bjavapode.repository.TransactionRepository;
import com.example.rewardyourteachersq011bjavapode.service.TransactionService;
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
class TransactionServiceImplTest {
    @Mock
    TransactionRepository transactionRepository;
    @InjectMocks
    TransactionServiceImpl transactionService;

    private List<Transaction> transactionList;
    private List<Message> messageList;
    private List<Notification> notificationList;
    private User user;
    private School school;
    private LocalDateTime localDateTime;


    @BeforeEach

    void setUp() {
        localDateTime = LocalDateTime.of(2022, Month.SEPTEMBER,5,3,7);
        User user = new User(1L,localDateTime,localDateTime,"Nma", Role.STUDENT,"Nma@gmail.com","123","about","0907777",transactionList,messageList,notificationList,"FGGC");
    }
    @Test
    void findAllTransactionForAUser(){
        when(transactionRepository.findAllByUser_Id(1L)).thenReturn(transactionList);
        var actual = transactionRepository.findAllByUser_Id(1L);
        assertEquals(actual, transactionRepository.findAllByUser_Id(1L));
    }

@Test
    void findAllNoTransactionForAUser(){
    when(transactionRepository.findAllByUser_Id(1L)).thenReturn(transactionList);
    var actual = transactionRepository.findAllByUser_Id(7L);
    assertEquals(actual, transactionRepository.findAllByUser_Id(7L));

}
}