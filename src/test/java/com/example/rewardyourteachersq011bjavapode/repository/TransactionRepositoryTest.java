package com.example.rewardyourteachersq011bjavapode.repository;

import com.example.rewardyourteachersq011bjavapode.enums.Role;
import com.example.rewardyourteachersq011bjavapode.enums.TransactionType;
import com.example.rewardyourteachersq011bjavapode.models.Message;
import com.example.rewardyourteachersq011bjavapode.models.Notification;
import com.example.rewardyourteachersq011bjavapode.models.Transaction;
import com.example.rewardyourteachersq011bjavapode.models.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
 class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;
    private List<Transaction> transactionList;
    private List<Message> messageList;
    private List<Notification> notificationList;
    private LocalDateTime localDateTime;
    private User user;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        user = new User(1L,localDateTime, localDateTime, "Nma", Role.STUDENT, "Nma@gmail.com", "1234","about","0906666", transactionList, messageList, notificationList, "VGC");
        localDateTime = LocalDateTime.of(2022, Month.FEBRUARY, 12, 12, 12);
        userRepository.save(user);
        transactionRepository.saveAllAndFlush(List.of(
                new Transaction("101", TransactionType.DEBIT, "vincent sent me money", user),
                new Transaction("102", TransactionType.DEBIT, "wallet funded", user),
                new Transaction("103", TransactionType.DEBIT, "deposit", user)
        ));
    }

    @Test
    void testToAssertThatATransactionIsFound() {
        List<Transaction> transaction = transactionRepository.findAllByUser_Id(1L);
        assertThat(transaction.size()).isEqualTo(3);
    }

    @Test
    void testToAssertThatATransactionIsNotFound() {
        List<Transaction> transaction = transactionRepository.findAllByUser_Id(5L);
        assertThat(transaction.size()).isEqualTo(0);

    }
}