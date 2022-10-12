package com.example.rewardyourteachersq011bjavapode.models;

import com.example.rewardyourteachersq011bjavapode.enums.NotificationType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notification")
public class Notification extends BaseClass{

    private String notificationBody;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId" , referencedColumnName = "id")
    private User user;



}
