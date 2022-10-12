package com.example.rewardyourteachersq011bjavapode.serviceImpl;
import com.example.rewardyourteachersq011bjavapode.enums.TransactionType;
import com.example.rewardyourteachersq011bjavapode.exceptions.UserNotFoundException;
import com.example.rewardyourteachersq011bjavapode.models.Transaction;
import com.example.rewardyourteachersq011bjavapode.models.User;
import com.example.rewardyourteachersq011bjavapode.repository.TransactionRepository;
import com.example.rewardyourteachersq011bjavapode.repository.UserRepository;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.service.TransactionService;
import com.example.rewardyourteachersq011bjavapode.utils.AppUUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final AppUUIDGenerator appUUIDGenerator;


    @Override
    public ApiResponse<List<Transaction>> findAllTransactionForAUser(Long user_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return new ApiResponse<>("success", LocalDateTime.now(), transactionRepository.findAllByUser_Id(user.getId()));
    }


}
