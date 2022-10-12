package com.example.rewardyourteachersq011bjavapode.serviceImpl;

import com.example.rewardyourteachersq011bjavapode.enums.NotificationType;
import com.example.rewardyourteachersq011bjavapode.exceptions.UserNotFoundException;
import com.example.rewardyourteachersq011bjavapode.models.Notification;
import com.example.rewardyourteachersq011bjavapode.models.User;
import com.example.rewardyourteachersq011bjavapode.repository.NotificationRepository;
import com.example.rewardyourteachersq011bjavapode.repository.UserRepository;
import com.example.rewardyourteachersq011bjavapode.service.NotificationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    private final UserRepository userRepository;


    @Override
    public Notification saveNotification(Long userId , String message , NotificationType notificationType) {
        Notification notification = new Notification();
        User user = findUserById(userId);
        notification.setNotificationBody(message);
        notification.setUser(user);
        notification.setNotificationType(notificationType);
        return notificationRepository.save(notification);
    }


    @Override
    public Notification saveNotification(String email , String message , NotificationType notificationType) {
        User user = userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException("User not found"));
        return notificationRepository.save(new Notification(message, notificationType, user));
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));


    }
}
