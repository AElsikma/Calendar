package com.example.casecalendar.controller;


import com.example.casecalendar.entitiy.Event;
import com.example.casecalendar.service.concretes.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/add")
    public Event addEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }

    @GetMapping("/daily/{userId}")
    public List<Event> getDailyEvents(@PathVariable Long userId, @RequestParam String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        return eventService.getDailyEvents(userId, localDateTime);
    }

    @GetMapping("/weekly/{userId}")
    public List<Event> getWeeklyEvents(@PathVariable Long userId, @RequestParam String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        return eventService.getWeeklyEvents(userId, localDateTime);
    }

    @GetMapping("/monthly/{userId}")
    public List<Event> getMonthlyEvents(@PathVariable Long userId, @RequestParam String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        return eventService.getMonthlyEvents(userId, localDateTime);
    }
}