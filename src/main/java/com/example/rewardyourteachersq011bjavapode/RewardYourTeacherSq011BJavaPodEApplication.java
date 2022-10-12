package com.example.rewardyourteachersq011bjavapode;
import com.example.rewardyourteachersq011bjavapode.serviceImpl.SchoolServiceImpl;
import com.example.rewardyourteachersq011bjavapode.utils.ListOfSchoolUtil;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class RewardYourTeacherSq011BJavaPodEApplication  {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(RewardYourTeacherSq011BJavaPodEApplication.class, args);

    }

}
