package com.example.todoapp;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private int idCounter = 1;

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(idCounter++);
        tasks.add(task);
        return task;
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setName(updatedTask.getName());
                task.setCompleted(updatedTask.isCompleted());
                return task;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        tasks.removeIf(task -> task.getId() == id);
        return "Task deleted";
    }
}