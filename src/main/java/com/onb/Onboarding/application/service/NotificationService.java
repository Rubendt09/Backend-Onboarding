package com.onb.Onboarding.application.service;

import com.onb.Onboarding.domain.model.Notification;
import com.onb.Onboarding.infrastructure.adapters.dto.NotificationResponseDTO;
import com.onb.Onboarding.infrastructure.adapters.ports.out.NotificationRepository;
import com.onb.Onboarding.infrastructure.adapters.ports.out.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public Notification createNotificationForAll(String title, String message, String urlImage, Date shippDate) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setUrlImage(urlImage);
        notification.setShippDate(shippDate);
        notification.setForAll(true);  // ForAll se establece a true
        return notificationRepository.save(notification);
    }

    public Notification createNotificationForSpecificUsers(String title, String message, String urlImage, Date shippDate, List<String> userIds) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setUrlImage(urlImage);
        notification.setShippDate(shippDate);
        notification.setForAll(false);

        notification.setUserIds(userIds);
        return notificationRepository.save(notification);
    }

    // Mostrar todas las notificaciones sin excepción
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Mostrar todas las notificaciones para todos los usuarios (forAll = true)
    public List<Notification> getNotificationsForAll() {
        return notificationRepository.findByForAllTrue();
    }

    // Mostrar notificaciones para un usuario específico por su ID
    public List<NotificationResponseDTO> getNotificationsForUser(String userId) {
        List<Notification> notifications = notificationRepository.findByUserIdsContaining(userId);

        // Mapeamos las notificaciones a la clase de respuesta
        return notifications.stream().map(notification ->
                new NotificationResponseDTO(
                        notification.getTitle(),
                        notification.getMessage(),
                        notification.getUrlImage(),
                        notification.getShippDate())
        ).collect(Collectors.toList());
    }

    public Notification updateNotification(String id, String title, String message, String urlImage, Date shippDate, boolean forAll, List<String> userIds) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));

        notification.setTitle(title);
        notification.setMessage(message);
        notification.setUrlImage(urlImage);
        notification.setShippDate(shippDate);
        notification.setForAll(forAll);

        // Condicional para el campo userIds basado en el valor de forAll
        if (forAll) {
            notification.setUserIds(null); // Aseguramos que la lista de usuarios esté vacía
        } else {
            if (userIds == null || userIds.isEmpty()) {
                throw new IllegalArgumentException("Para notificaciones específicas, la lista de usuarios no puede estar vacía");
            }
            notification.setUserIds(userIds); // Solo si forAll es false, se asigna userIds
        }

        return notificationRepository.save(notification);
    }


    // Borrar notificación por su ID
    public void deleteNotificationById(String id) {
        notificationRepository.deleteById(id);
    }
}
