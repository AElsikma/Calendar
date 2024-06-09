package com.example.casecalendar.repository;

import com.example.casecalendar.entitiy.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByUserIdAndStartDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);


    List<Event> findByUserId(Long userId);
}