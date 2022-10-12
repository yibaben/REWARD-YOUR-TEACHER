//package com.example.rewardyourteachersq011bjavapode.service;
//
//import com.example.rewardyourteachersq011bjavapode.dto.TeacherDetails;
//import com.example.rewardyourteachersq011bjavapode.repository.TeacherRepository;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class ITeacherServiceTest {
//
//    @Autowired
//    private ITeacherService iTeacherService;
//
//    @MockBean
//    private TeacherRepository teacherRepository;
//
//    @BeforeEach
//    void setUp() {
//        TeacherDetails teacherDetails = TeacherDetails.builder()
//                .name("Bennett Yibawenifiri")
//                .school("Lagos Grammar School")
//                .teachingPeriod("2020 t0 2022")
//                .build();
//
//        Mockito.when(teacherRepository.findAllBySchool("Lagos Grammar School", Pageable.ofSize(5)));
//    }
//
//    @Test
////    @Disabled
//    @DisplayName("retrieve All Teachers In A Particular School")
//    public void retrieveAllTeachersInAParticularSchool() {
//        int pageNo = 1;
//        int pageSize = 5;
//        String schoolName = "Lagos Grammer School";
//        List<TeacherDetails> teacherDetails = iTeacherService.getAllTeacherBySchoolWithPagination(pageNo, pageSize, schoolName);
//        assertEquals(pageNo, pageSize, schoolName);
//
//    }
//}