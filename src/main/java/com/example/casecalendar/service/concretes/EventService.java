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
}