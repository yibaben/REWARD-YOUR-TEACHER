package com.example.rewardyourteachersq011bjavapode.service;

import com.example.rewardyourteachersq011bjavapode.models.School;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import org.springframework.data.domain.Page;

import java.io.BufferedReader;
import java.util.List;

public interface SchoolService {
    String addSchool(BufferedReader bufferedReader);
    ApiResponse<Page<School>> getAllSchools(int page , int size , String sortByName);
}
