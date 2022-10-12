package com.example.rewardyourteachersq011bjavapode.controllers;

import com.example.rewardyourteachersq011bjavapode.service.ITeacherService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TeacherControllerTest {
    @Mock
    private ITeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    @Test
    @DisplayName("Should return all teachers with pagination")
    void getAllTeachersWithPaginationShouldReturnAllTeachersWithPagination() {
        int pageNo = 1;
        int pageSize = 10;
        teacherController.getAllTeachersWithPagination(pageNo, pageSize);
        verify(teacherService, times(1)).getAllTeachersWithPagination(pageNo, pageSize);
    }
}