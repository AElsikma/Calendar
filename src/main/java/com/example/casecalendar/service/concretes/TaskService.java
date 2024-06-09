package com.example.casecalendar.service.concretes;

import com.example.casecalendar.entitiy.Task;
import com.example.casecalendar.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getDailyTasks(Long userId, LocalDateTime date) {
        LocalDateTime startOfDay = date.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = date.toLocalDate().atTime(23, 59, 59);
        return taskRepository.findByUserIdAndStartDateBetween(userId, startOfDay, endOfDay);
    }

    public List<Task> getWeeklyTasks(Long userId, LocalDateTime date) {
        LocalDateTime startOfWeek = date.with(java.time.DayOfWeek.MONDAY).toLocalDate().atStartOfDay();
        LocalDateTime endOfWeek = date.with(java.time.DayOfWeek.SUNDAY).toLocalDate().atTime(23, 59, 59);
        return taskRepository.findByUserIdAndStartDateBetween(userId, startOfWeek, endOfWeek);
    }

    public List<Task> getMonthlyTasks(Long userId, LocalDateTime date) {
        LocalDateTime startOfMonth = date.withDayOfMonth(1).toLocalDate().atStartOfDay();
        LocalDateTime endOfMonth = date.withDayOfMonth(date.toLocalDate().lengthOfMonth()).toLocalDate().atTime(23, 59, 59);
        return taskRepository.findByUserIdAndStartDateBetween(userId, startOfMonth, endOfMonth);
    }
}