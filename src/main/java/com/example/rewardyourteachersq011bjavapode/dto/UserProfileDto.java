package com.example.rewardyourteachersq011bjavapode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
    private String about;
    private String name;
    private String School;
    private String email;
    private String post;
    private String Telephone;
}
