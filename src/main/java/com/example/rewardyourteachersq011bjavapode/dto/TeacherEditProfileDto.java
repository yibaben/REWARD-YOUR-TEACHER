package com.example.rewardyourteachersq011bjavapode.dto;

import com.example.rewardyourteachersq011bjavapode.enums.SchoolType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeacherEditProfileDto extends UserEditProfileDto{

    private String teachingPeriod;
    private SchoolType schoolType;
}
