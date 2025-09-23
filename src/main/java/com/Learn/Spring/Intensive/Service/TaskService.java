package com.Learn.Spring.Intensive.Service;


import com.Learn.Spring.Intensive.Entity.Status;
import com.Learn.Spring.Intensive.Entity.Task;
import com.Learn.Spring.Intensive.Entity.TaskEntity;
import com.Learn.Spring.Intensive.Repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTaskById(Long taskId){

        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(()->new EntityNotFoundException("No task with such taskId "+taskId));

        return toDomain(taskEntity);
    }

    public List<Task> getAllTasks(){

        List<TaskEntity> taskEntities = taskRepository.findAll();
        return taskEntities.stream().map(
                this::toDomain
        ).toList();
    }

    public Task createTask(Task task){
        if(task.getTaskId()!=null || task.getStatus()!=null){
            throw new IllegalArgumentException("Adding taskId or status not allowed");
        }
        var taskEntity = new TaskEntity(
                null,
                task.getCreatorId(),
                task.getAssignedUserId(),
                Status.CREATED,
                task.getCreateDateTime(),
                task.getDeadlineDate(),
                task.getPriority()
        );
        var savedTaskEntity = taskRepository.save(taskEntity);
        return toDomain(savedTaskEntity);
    }

    public Task updateTask(Long taskId, Task taskToUpdate) {
        if(taskToUpdate.getTaskId()!=null || taskToUpdate.getStatus()!=null){
            throw new IllegalArgumentException("Adding taskId or status not allowed");
        }

        var selectedTask = taskRepository.findById(taskId)
                .orElseThrow(()-> new EntityNotFoundException("No task with taskId = "+taskId));
        var taskToSave = new TaskEntity(
                selectedTask.getTaskId(),
                taskToUpdate.getCreatorId(),
                taskToUpdate.getAssignedUserId(),
                selectedTask.getStatus(),
                taskToUpdate.getCreateDateTime(),
                taskToUpdate.getDeadlineDate(),
                taskToUpdate.getPriority()
        );
        var savedTask = taskRepository.save(taskToSave);
        return toDomain(savedTask);
    }

    public void deleteTask(Long taskId) {

        var taskToDelete = taskRepository.findById(taskId)
                .orElseThrow(()-> new EntityNotFoundException("No task with taskId = "+taskId));
        taskRepository.delete(taskToDelete);
    }

    public void startTask(Long taskId) {
        Long count;
        var selectedTask = taskRepository.findById(taskId).orElseThrow(()->new EntityNotFoundException("No task with taskId = "+taskId));
        count = taskRepository.CountAssignedUsersWithStatusInProgress(selectedTask.getAssignedUserId());
        System.out.println(count);
        if (count>4){
            throw new IllegalStateException("Illegal to start task for user = " + selectedTask.getAssignedUserId()+ " with five active tasks" );
        }
        selectedTask.setStatus(Status.IN_PROGRESS);
        taskRepository.save(selectedTask);
    }

    private Task toDomain(TaskEntity taskEntities){
        return new Task(
                taskEntities.getTaskId(),
                taskEntities.getCreatorId(),
                taskEntities.getAssignedUserId(),
                taskEntities.getStatus(),
                taskEntities.getCreateDateTime(),
                taskEntities.getDeadlineDate(),
                taskEntities.getPriority()
        );
    }

}
