package com.onb.Onboarding.infrastructure.adapters.ports.out;

import com.onb.Onboarding.domain.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByForAllTrue();  // Buscar todas las notificaciones con forAll = true
    List<Notification> findByUserIdsContaining(String userId);  // Buscar notificaciones asignadas a un usuario específico
}
