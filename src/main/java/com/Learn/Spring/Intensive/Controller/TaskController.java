package com.Learn.Spring.Intensive.Controller;


import com.Learn.Spring.Intensive.Entity.Task;
import com.Learn.Spring.Intensive.Service.TaskService;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    private final TaskService taskService;

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId){
        log.info("getTaskById");
        return taskService.getTaskById(taskId);
    }

    @GetMapping
    public List<Task> getAllTasks(){
        log.info("getAllTasks");
        return taskService.getAllTasks();
    }

//    @PostMapping("/save_task")
//    public Task saveTask(@RequestBody Task task){
//        log.info("Called saveTask");
//        return taskService.saveTask(task);
//    }

}
