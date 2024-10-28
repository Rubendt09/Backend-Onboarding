package com.onb.Onboarding.infrastructure.adapters.controller;

import com.onb.Onboarding.application.service.EventService;
import com.onb.Onboarding.domain.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Crear un nuevo evento
    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event newEvent = eventService.createEvent(event);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    // Actualizar un evento existente
    @PutMapping("update/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable String id, @RequestBody Event eventDetails) {
        Event updatedEvent = eventService.updateEvent(id, eventDetails);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    // Obtener todos los eventos
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // Obtener todos los eventos desde la fecha de hoy en adelante
    @GetMapping("/upcoming")
    public ResponseEntity<List<Event>> getUpcomingEvents() {
        List<Event> upcomingEvents = eventService.getUpcomingEvents();
        return new ResponseEntity<>(upcomingEvents, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  void deleteEventById(@PathVariable String id){
        eventService.deleteEventById(id);
    }
}
