package com.example.rewardyourteachersq011bjavapode.models;

import com.example.rewardyourteachersq011bjavapode.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@Table(name = "users")
public class User  extends BaseClass implements Serializable{
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true)
    private String email;

    private String password;

    private String about;
    private String telephone;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactionList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Message> messageList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Notification> notificationList = new ArrayList<>();


    private String school;

    public User(Long id, LocalDateTime createDate, LocalDateTime updateDate, String name, Role role, String email, String password,String about,String telephone, List<Transaction> transactionList, List<Message> messageList, List<Notification> notificationList, String school) {
        super(id, createDate, updateDate);
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
        this.transactionList = transactionList;
        this.messageList = messageList;
        this.notificationList = notificationList;
        this.school = school;
        this.telephone =telephone;
        this.about = about;
    }
}

