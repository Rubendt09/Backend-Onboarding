package com.onb.Onboarding.application.service;

import com.onb.Onboarding.domain.model.Event;
import com.onb.Onboarding.infrastructure.adapters.ports.out.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event){
        return  eventRepository.save(event);
    }

    public Event updateEvent(String id, Event eventDetails) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event existingEvent = optionalEvent.get();
            existingEvent.setTitle(eventDetails.getTitle());
            existingEvent.setMessage(eventDetails.getMessage());
            existingEvent.setUrlImage(eventDetails.getUrlImage());
            existingEvent.setStartDate(eventDetails.getStartDate());
            return eventRepository.save(existingEvent);
        } else {
            throw new RuntimeException("Evento no encontrado con ID: " + id);
        }
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Obtener todos los eventos desde la fecha de hoy en adelante
    public List<Event> getUpcomingEvents() {
        Date today = new Date();
        return eventRepository.findByStartDateAfter(today);
    }

    public  void deleteEventById(String id){
        eventRepository.deleteById(id);
    }
}
