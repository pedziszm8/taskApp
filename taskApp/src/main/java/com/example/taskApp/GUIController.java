package com.example.taskApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GUIController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/displayvariable")
    public String displayVariable(Model model){
        String wyswietl="definicja";

        model.addAttribute("mojaZmienna",wyswietl);
        return "displayVariable";
    }
    @GetMapping("/taskList")
    public String showTaskList(Model model){
        List<Task> taskList = taskRepository.findAll();
        model.addAttribute("tasks",taskList);
        return "taskList";
    }
    @GetMapping("/")
    public String showHomePage(){
        return "redirect:/taskList";
    }

    @GetMapping("/addNewTask")
    public String showAddTaskForm(Model model){
        model.addAttribute("newTask",new Task());
      return "addNewTask";
    }

    @PostMapping("/addNewTask")
    public String addNewTask(@ModelAttribute Task task){
        taskRepository.save(task);
        return "redirect:/taskList";

    }
}
