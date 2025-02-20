package com.example.taskApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class GUIController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/displayvariable")
    public String displayVariable(Model model) {
        String wyswietl = "definicja";

        model.addAttribute("mojaZmienna", wyswietl);
        return "displayVariable";
    }

    @GetMapping("/taskList")
    public String showTaskList(Model model) {
        List<Task> taskList = taskRepository.findAll();
        model.addAttribute("tasks", taskList);
        return "taskList";
    }

    @GetMapping("/")
    public String showHomePage() {
        return "redirect:/taskList";
    }

    @GetMapping("/addNewTask")
    public String showAddTaskForm(Model model) {
        model.addAttribute("newTask", new Task());
        return "addNewTask";
    }

    @PostMapping("/addNewTask")
    public String addNewTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/taskList";

    }

    @PostMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable("id") long id) {
        System.out.println("Id suswanego taska:" + id);
        taskRepository.deleteById(id);
        return "redirect:/taskList";
    }

    @PostMapping("/editTask/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Optional<Task> byId = taskRepository.findById(id);
        Task task = byId.orElseThrow(() -> new RuntimeException("Invalid id"));
        model.addAttribute("task", task);
        return "editTask";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task){
        taskRepository.save(task);
        return "redirect:/taskList";
    }
}
