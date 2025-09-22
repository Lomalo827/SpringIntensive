package com.Learn.Spring.Intensive.Service;


import com.Learn.Spring.Intensive.Entity.Status;
import com.Learn.Spring.Intensive.Entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class TaskService {

    private HashMap<Long,Task> taskBucket = new HashMap<>();
    private static Long taskCounter=0L;


    public Task getTaskById(Long taskId){

        if (!taskBucket.containsKey(taskId)){
            throw new NoSuchElementException("No task with such taskId");
        }
        return taskBucket.get(taskId);
    }

    public List<Task> getAllTasks(){
        return taskBucket.values().stream().toList();
    }

    public Task createTask(Task task){
        if(task.getTaskId()!=null || task.getStatus()!=null){
            throw new IllegalArgumentException("Adding taskId or status not allowed");
        }
        taskBucket.put(taskCounter+=1,new Task(
                taskCounter,
                task.getCreatorId(),
                Status.CREATED,
                task.getCreateDateTime(),
                task.getDeadlineDate(),
                task.getPriority()
        ));
        return taskBucket.get(taskCounter);
    }

    public Task updateTask(Long taskId, Task taskToUpdate) {
        if(taskToUpdate.getTaskId()!=null || taskToUpdate.getStatus()!=null){
            throw new IllegalArgumentException("Adding taskId or status not allowed");
        }
        if (!taskBucket.containsKey(taskId)){
            throw new NoSuchElementException("No task with taskId = "+taskId);
        }
        if (taskBucket.get(taskId).getStatus()==Status.DONE){
            throw new IllegalStateException("Illegal to update task with status = DONE");
        }

        var updatedTask = new Task(
                taskBucket.get(taskId).getTaskId(),
                taskToUpdate.getCreatorId(),
                taskBucket.get(taskId).getStatus(),
                taskToUpdate.getCreateDateTime(),
                taskToUpdate.getDeadlineDate(),
                taskToUpdate.getPriority()
        );
        taskBucket.put(taskId,updatedTask);
        return taskBucket.get(taskId);
    }

    public void deleteTask(Long taskId) {
        if (!taskBucket.containsKey(taskId)){
            throw new NoSuchElementException("No task with taskId = "+taskId);
        }
        taskBucket.remove(taskId);
    }
}
