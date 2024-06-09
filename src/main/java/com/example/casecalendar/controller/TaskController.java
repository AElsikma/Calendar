package com.example.casecalendar.controller;

import com.example.casecalendar.entitiy.Task;
import com.example.casecalendar.service.concretes.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping("/daily/{userId}")
    public List<Task> getDailyTasks(@PathVariable Long userId, @RequestParam String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        return taskService.getDailyTasks(userId, localDateTime);
    }

    @GetMapping("/weekly/{userId}")
    public List<Task> getWeeklyTasks(@PathVariable Long userId, @RequestParam String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        return taskService.getWeeklyTasks(userId, localDateTime);
    }

    @GetMapping("/monthly/{userId}")
    public List<Task> getMonthlyTasks(@PathVariable Long userId, @RequestParam String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        return taskService.getMonthlyTasks(userId, localDateTime);
    }
}