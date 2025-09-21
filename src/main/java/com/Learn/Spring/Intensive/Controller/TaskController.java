package com.Learn.Spring.Intensive.Controller;


import com.Learn.Spring.Intensive.Entity.Task;
import com.Learn.Spring.Intensive.Service.TaskService;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        log.info("getTaskById");
        return taskService.getTaskById(id);
    }

    @GetMapping
    public List<Task> getAllTasks(){
        log.info("getAllTasks");
        return taskService.getAllTasks();
    }

}
