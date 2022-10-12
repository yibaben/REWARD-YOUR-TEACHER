package com.example.rewardyourteachersq011bjavapode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrincipalDto {
    private Long id;
    private String name;
    private String email;
    private String token;
}
