package com.example.rewardyourteachersq011bjavapode.service;

import com.example.rewardyourteachersq011bjavapode.dto.InitializeTransactionRequest;
import com.example.rewardyourteachersq011bjavapode.exceptions.WalletNotFoundException;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;

public interface RewardService {

    ApiResponse<String> rewardTeacherByTeacherId(InitializeTransactionRequest request)
            throws WalletNotFoundException;
}
