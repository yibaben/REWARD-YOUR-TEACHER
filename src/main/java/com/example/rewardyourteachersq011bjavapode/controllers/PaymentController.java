package com.example.rewardyourteachersq011bjavapode.controllers;


import com.example.rewardyourteachersq011bjavapode.dto.InitializeTransactionRequest;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.response.InitializeTransactionResponse;
import com.example.rewardyourteachersq011bjavapode.response.VerifyTransactionResponse;
import com.example.rewardyourteachersq011bjavapode.service.PaymentService;
import com.example.rewardyourteachersq011bjavapode.service.RewardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PaymentController {
    private PaymentService paymentService;

    private RewardService rewardService;

    @PostMapping("/make-payment")
    public ResponseEntity<?> pay(@RequestBody InitializeTransactionRequest request) throws Exception {
        return ResponseEntity.ok(paymentService.initTransaction(request));
    }

    @GetMapping("/verify-transaction")
    public ResponseEntity<VerifyTransactionResponse> verifyTransaction(String reference) {
        return ResponseEntity.ok(paymentService.verifyTransaction(reference));
    }

    @PostMapping("/rewardTeacher")
    public ResponseEntity<ApiResponse<String>> rewardTeacher(@RequestBody InitializeTransactionRequest request) {
        return ResponseEntity.ok(rewardService.rewardTeacherByTeacherId(request));
    }
}
