package com.example.casecalendar.repository;


import com.example.casecalendar.entitiy.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserIdAndStartDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}
