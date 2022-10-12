package com.example.rewardyourteachersq011bjavapode.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Data
public class AppUUIDGenerator {
    private UUID uuid = UUID.randomUUID();

    private String uuidAsString = uuid.toString();
}