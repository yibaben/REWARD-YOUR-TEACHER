package com.example.rewardyourteachersq011bjavapode.serviceImpl;

import com.example.rewardyourteachersq011bjavapode.dto.TeacherDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TeacherServiceImplTest {
    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @Test
    @DisplayName("Should return the list of teachers with pagination")
    void getAllTeachersWithPaginationShouldReturnListOfTeachersWithPagination() {
        int pageNo = 1;
        int pageSize = 2;
        Page<TeacherDetails> teacherDetailsPage =
                teacherServiceImpl.getAllTeachersWithPagination(pageNo, pageSize);
        assertEquals(2, teacherDetailsPage.getSize());
    }

    @Test
    void testGetAllTeachersWithPagination() {

        assertTrue(teacherServiceImpl.getAllTeachersWithPagination(1, 3).toList().isEmpty());
        assertTrue(teacherServiceImpl.getAllTeachersWithPagination(Integer.MIN_VALUE, 3).toList().isEmpty());
    }
}

