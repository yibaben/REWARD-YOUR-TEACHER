package com.example.rewardyourteachersq011bjavapode.utils;


import com.example.rewardyourteachersq011bjavapode.dto.SchoolDTO;
import com.example.rewardyourteachersq011bjavapode.models.School;
import lombok.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ListOfSchoolUtil {

   // private static BufferedReader reader;
    private static String line;

    public ListOfSchoolUtil() {

    }


    public static List<SchoolDTO> readAllSchoolsFromCsvFile(BufferedReader bufferedReader){
        List<SchoolDTO> schools = new ArrayList<>();
        try {
          //  reader = new BufferedReader(new FileReader(path));
            String[] data = new String[0];
            while((line = bufferedReader.readLine()) != null){
                while((line = bufferedReader.readLine()) != null){
                    data = line.split(",");
                    SchoolDTO schoolDTO =  new SchoolDTO(data[0] , data[1] , data[2] , data[3]);
                    schools.add(schoolDTO);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return schools;
    }
}
