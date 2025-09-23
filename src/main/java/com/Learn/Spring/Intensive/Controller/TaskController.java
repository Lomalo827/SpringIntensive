package com.Learn.Spring.Intensive.Controller;

import com.Learn.Spring.Intensive.Entity.Task;
import com.Learn.Spring.Intensive.Service.TaskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task>getTaskById(@PathVariable Long taskId){
        log.info("Called getTaskById with taskId = {}",taskId);
        return ResponseEntity.ok().body(taskService.getTaskById(taskId));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        log.info("Called getAllTasks");
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

    @PostMapping("/save_task")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        log.info("Called saveTask");
        return ResponseEntity.status(201).body(taskService.createTask(task));
    }

    @PostMapping("/{taskId}/start")
    public void startTask(@PathVariable Long taskId){
        log.info("Called startTask for task with id = {}",taskId);
        taskService.startTask(taskId);
//        return ResponseEntity.status(200).body(taskService.startTask(taskId));
    }

    @PutMapping("/update_task/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task taskToUpdate){
        log.info("Called updateTask for task with id = {}",taskId);
        return ResponseEntity.status(200).body(taskService.updateTask(taskId,taskToUpdate));
    }

    @DeleteMapping("delete/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }

}
