package com.example.casecalendar.service.concretes;

import com.example.casecalendar.entitiy.Event;
import com.example.casecalendar.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event addEvent(Event event) {

        if (event.getUser() == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (event.getTitle() == null || event.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (event.getStartDate() == null || event.getEndDate() == null) {
            throw new IllegalArgumentException("Start and end dates cannot be null");
        }
        if (event.getStartDate().isAfter(event.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        return eventRepository.save(event);
    }


    public List<Event> getDailyEvents(Long userId, LocalDateTime date) {
        LocalDateTime startOfDay = date.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = date.toLocalDate().atTime(23, 59, 59);
        return eventRepository.findByUserIdAndStartDateBetween(userId, startOfDay, endOfDay);
    }

    public List<Event> getWeeklyEvents(Long userId, LocalDateTime date) {
        LocalDateTime startOfWeek = date.with(java.time.DayOfWeek.MONDAY).toLocalDate().atStartOfDay();
        LocalDateTime endOfWeek = date.with(java.time.DayOfWeek.SUNDAY).toLocalDate().atTime(23, 59, 59);
        return eventRepository.findByUserIdAndStartDateBetween(userId, startOfWeek, endOfWeek);
    }

    public List<Event> getMonthlyEvents(Long userId, LocalDateTime date) {
        LocalDateTime startOfMonth = date.withDayOfMonth(1).toLocalDate().atStartOfDay();
        LocalDateTime endOfMonth = date.withDayOfMonth(date.toLocalDate().lengthOfMonth()).toLocalDate().atTime(23, 59, 59);
        return eventRepository.findByUserIdAndStartDateBetween(userId, startOfMonth, endOfMonth);
    }

    public Event updateEvent(Long eventId, Event updatedEvent) {
        // Check if the event with eventId exists
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        // Update the fields of existingEvent with the fields of updatedEvent
        existingEvent.setTitle(updatedEvent.getTitle());
        existingEvent.setStartDate(updatedEvent.getStartDate());
        existingEvent.setEndDate(updatedEvent.getEndDate());

        // Save the updated event
        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(Long eventId) {
        // Check if the event with eventId exists
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        // Delete the event
        eventRepository.delete(existingEvent);
    }
    public List<Event> getAllEvents(Long userId) {
        return eventRepository.findByUserId(userId);
    }

}