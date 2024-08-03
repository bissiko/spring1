package com.javarush.controller;

import com.javarush.domain.Task;
import com.javarush.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequestMapping
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    //@GetMapping("/")
    public String tasks(Model model,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

        List<Task> tasks = taskService.getAll((page - 1) * limit, limit);
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    //Todo - у всих 3 методів
    @PostMapping("/{id}")
    public String edit(Model model,
                     @PathVariable Integer id,
                     @RequestBody TaskInfo info) {

        if(isNull(id) || id < 0) {
            throw new RuntimeException("Invalid id");
        }
        Task task = taskService.edit(id, info.getDescription(), info.getStatus());
        return tasks(model, 1, 10); //das
    }

    @PostMapping("/")
    public String add(Model model, @RequestBody TaskInfo info) {
        Task task = taskService.create(info.getDescription(), info.getStatus());
        return tasks(model, 1, 10); //das
    }

    @DeleteMapping("/{id}")
    public String delete(Model model, @PathVariable Integer id) {
        if(isNull(id) || id < 0) {
            throw new RuntimeException("Invalid id");
        }
        taskService.delete(id);
        return tasks(model, 1, 10); //das
    }
}
