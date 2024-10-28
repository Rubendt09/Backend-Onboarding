package com.onb.Onboarding.infrastructure.adapters.controller;

import com.onb.Onboarding.application.service.NotificationService;
import com.onb.Onboarding.domain.model.Notification;
import com.onb.Onboarding.infrastructure.adapters.dto.NotificationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Crear notificación para todos
    @PostMapping("/createForAll")
    public Notification createNotificationForAll(@RequestBody Notification request) {
        return notificationService.createNotificationForAll(
                request.getTitle(),
                request.getMessage(),
                request.getUrlImage(),
                request.getShippDate());
    }


    // Crear notificación para usuarios específicos (usando solo los userIds)
    @PostMapping("/createForUsers")
    public Notification createNotificationForSpecificUsers(
            @RequestBody Notification request) {
        return notificationService.createNotificationForSpecificUsers(
                request.getTitle(),
                request.getMessage(),
                request.getUrlImage(),
                request.getShippDate(),
                request.getUserIds());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    // Mostrar todas las notificaciones para todos (forAll = true)
    @GetMapping("/forAll")
    public List<Notification> getNotificationsForAll() {
        return notificationService.getNotificationsForAll();
    }

    // Mostrar notificaciones para un usuario específico
    @GetMapping("/forUser/{userId}")
    public List<NotificationResponseDTO> getNotificationsForUser(@PathVariable String userId) {
        return notificationService.getNotificationsForUser(userId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNotification(
            @PathVariable String id,
            @RequestBody Notification notificationRequest) {

        try {
            Notification updatedNotification = notificationService.updateNotification(
                    id,
                    notificationRequest.getTitle(),
                    notificationRequest.getMessage(),
                    notificationRequest.getUrlImage(),
                    notificationRequest.getShippDate(),
                    notificationRequest.isForAll(),
                    notificationRequest.getUserIds()
            );

            return ResponseEntity.ok(updatedNotification);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // Borrar notificación por ID
    @DeleteMapping("/{id}")
    public void deleteNotificationById(@PathVariable String id) {
        notificationService.deleteNotificationById(id);
    }
}
