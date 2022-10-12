package com.example.rewardyourteachersq011bjavapode.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "schools")
public class School extends BaseClass{

    private String name;
    private String address;
    private String stateAndCountry;
    private String schoolType;



}

