package com.example.rewardyourteachersq011bjavapode.utils;

import com.example.rewardyourteachersq011bjavapode.dto.SchoolDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListOfSchoolUtilTest {

    @Test
    void readAllSchoolsFromCsvFile() {
        Reader reader = new StringReader("name,address,state,type\nfgc,nise, Anambra,Secondary");
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<SchoolDTO> schoolDTOList = ListOfSchoolUtil.readAllSchoolsFromCsvFile(bufferedReader);
        assertThat(schoolDTOList.size()).isEqualTo(1);
    }

    @Test
    void testThatMethodThrowsFileNotFoundException() {
        assertThatThrownBy(()->{
            try {
                FileReader reader = null;
                reader = new FileReader("name,address,state,type\nfgc,nise, Anambra,Secondary");
                BufferedReader bufferedReader = new BufferedReader(reader);
                ListOfSchoolUtil.readAllSchoolsFromCsvFile(bufferedReader);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }).isInstanceOf(RuntimeException.class);
    }

}