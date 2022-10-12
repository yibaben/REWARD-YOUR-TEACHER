package com.example.rewardyourteachersq011bjavapode.service;

import com.example.rewardyourteachersq011bjavapode.dto.InitializeTransactionRequest;
import com.example.rewardyourteachersq011bjavapode.response.InitializeTransactionResponse;
import com.example.rewardyourteachersq011bjavapode.response.VerifyTransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface PaymentService {

    InitializeTransactionResponse initTransaction(InitializeTransactionRequest request) throws Exception;

    VerifyTransactionResponse verifyTransaction(String reference);
}
