package com.example.rewardyourteachersq011bjavapode.serviceImpl;

import com.example.rewardyourteachersq011bjavapode.dto.SchoolDTO;
import com.example.rewardyourteachersq011bjavapode.exceptions.ResourceNotFoundException;
import com.example.rewardyourteachersq011bjavapode.models.School;
import com.example.rewardyourteachersq011bjavapode.repository.SchoolRepository;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.service.SchoolService;
import com.example.rewardyourteachersq011bjavapode.utils.ListOfSchoolUtil;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.rewardyourteachersq011bjavapode.utils.ListOfSchoolUtil.readAllSchoolsFromCsvFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolServiceImpl implements SchoolService {


    private final SchoolRepository schoolRepository;


    private BufferedReader bufferedReader;
    @Override
    public String addSchool(BufferedReader bufferedReader ) {
        String message = "";
        List<SchoolDTO>schoolsToBeAdded = readAllSchoolsFromCsvFile(bufferedReader );
        List<School> schoolsInDatabase = schoolRepository.findAll();
        if(schoolsToBeAdded.size() > schoolsInDatabase.size()){
            schoolRepository.deleteAll();
            schoolsToBeAdded.forEach(school-> {
                schoolRepository.saveAndFlush(new School(school.getName() , school.getAddress() , school.getStateAndCountry(), school.getSchoolType()));
            });
            message = "Schools  Added";
           log.info(message);
       }else{
            message = "Schools Already  Added";
           log.info(message);
        }

        return message;
    }

    @Override
    public ApiResponse<Page<School>> getAllSchools(int page , int size , String sortByName) {
        try {
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/List Of Schools In Lagos - updated.csv"));
            addSchool(bufferedReader);
            Pageable pageWith5records = PageRequest.of(page, size, Sort.by(sortByName).ascending());
            Page<School> schoolList = schoolRepository.findAll(pageWith5records);
            return new ApiResponse<>("success" , LocalDateTime.now() , schoolList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
