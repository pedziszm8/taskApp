package com.example.taskApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        Task saved = taskRepository.save(task);
        return saved;
    }

    @GetMapping
    public List<Task> getAllTasks () {
        List<Task> taskList = taskRepository.findAll();
        return taskList;
    }
}
