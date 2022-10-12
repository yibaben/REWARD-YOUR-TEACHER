package com.example.rewardyourteachersq011bjavapode.repository;

import com.example.rewardyourteachersq011bjavapode.enums.Role;
import com.example.rewardyourteachersq011bjavapode.exceptions.ResourceNotFoundException;
import com.example.rewardyourteachersq011bjavapode.exceptions.UserNotFoundException;
import com.example.rewardyourteachersq011bjavapode.models.*;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityManagerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {


    @Autowired UserRepository userRepository;
    private LocalDateTime localDateTime;

    @BeforeEach
    void setUp() {
        localDateTime = LocalDateTime.of(2022, Month.SEPTEMBER , 5 , 5 , 5 , 3);
        List<Transaction> transactionList = new ArrayList<>();
        List<Message> messageList = new ArrayList<>();
        List<Notification> notificationList = new ArrayList<>();


        userRepository.saveAllAndFlush(
                List.of(
                        new User(1L , localDateTime , localDateTime , "vincent" , Role.STUDENT , "enwerevincent@gmail.com", "12345" ,"","", transactionList, messageList, notificationList , "fgc nise"),
                        new User(2L , localDateTime , localDateTime , "chioma" , Role.STUDENT , "chioma@gmail.com", "12345" , "","",transactionList, messageList, notificationList , "fggc onitsha"),
                        new User(3L , localDateTime , localDateTime , "cisca" , Role.STUDENT , "cisca@gmail.com", "12345" ,"","", transactionList, messageList, notificationList , "fgc nise")
                )
        );
    }

    @Test
    void findUserByEmailUserIsNotNullAndIdIsEqualTo1L() {
        User user = userRepository.findUserByEmail("enwerevincent@gmail.com")
                .orElseThrow(ResourceNotFoundException::new);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
    }

    @Test
    void findUserByEmailThrowsResourceNotFoundException(){
        assertThatThrownBy(()->{
           userRepository.findUserByEmail("invalidUser@gmail.com")
                    .orElseThrow(ResourceNotFoundException::new);
        }).isInstanceOf(ResourceNotFoundException.class);

    }

    @Test
    void findUserByIdIsNotNullAndIdIsEqualTo1L() {
        User user = userRepository.findById(1L)
                .orElseThrow(ResourceNotFoundException::new);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
    }



    @Test
    void findUserByIdThrowsResourceNotFoundException(){
        assertThatThrownBy(()->{
            userRepository.findById(5L)
                    .orElseThrow(ResourceNotFoundException::new);
        }).isInstanceOf(ResourceNotFoundException.class);

    }

}