package com.example.rewardyourteachersq011bjavapode.repository;

import com.example.rewardyourteachersq011bjavapode.models.School;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static com.example.rewardyourteachersq011bjavapode.enums.SchoolType.SECONDARY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest

class SchoolRepositoryTest {

    @Autowired  private  SchoolRepository schoolRepository;
    private School school;
    @BeforeEach
    void setUp() {
        school = new School("FGC", "Nise, Anambra State" , "Anambra, Nigeria" , "SECONDARY");
        schoolRepository.saveAndFlush(school);
    }

    @Test
    void testThatSchoolWasFound() {
        Optional<School> schoolTest = schoolRepository.findByName("FGC");
        assertThat(schoolTest).isPresent()
                .contains(school);
    }

    @Test
    void testThatSchoolWasNotFound(){
        Optional<School> schoolTest = schoolRepository.findByName("XXXX");
        assertThat(schoolTest).isEmpty();

    }
}