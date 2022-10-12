package com.example.rewardyourteachersq011bjavapode.models;


import com.example.rewardyourteachersq011bjavapode.enums.Role;
import com.example.rewardyourteachersq011bjavapode.enums.SchoolType;
import com.example.rewardyourteachersq011bjavapode.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;



@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue(value = "teacher")
public class Teacher extends User{

    private String teachingPeriod;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String Post;

    @Enumerated(EnumType.STRING)
    private SchoolType schoolType;



    private  String teacherIdUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Subject> subject;


    public Teacher(String name, Role role, String email, String password, String about, String telephone, List<Transaction> transactionList, List<Message> messageList, List<Notification> notificationList, String school, String teachingPeriod, Status status, String post, SchoolType schoolType, String teacherIdUrl, List<Subject> subject) {
        super(name, role, email, password, about, telephone, transactionList, messageList, notificationList, school);
        this.teachingPeriod = teachingPeriod;
        this.status = status;
        this.Post = post;
        this.schoolType = schoolType;
        this.teacherIdUrl = teacherIdUrl;
        this.subject = subject;
    }
}
