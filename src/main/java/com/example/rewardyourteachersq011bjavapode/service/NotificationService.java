package com.example.rewardyourteachersq011bjavapode.service;

import com.example.rewardyourteachersq011bjavapode.enums.NotificationType;
import com.example.rewardyourteachersq011bjavapode.models.Notification;
public interface NotificationService {
    Notification saveNotification(Long userId, String message , NotificationType notificationType);

    Notification saveNotification(String email, String message, NotificationType notificationType);
}
