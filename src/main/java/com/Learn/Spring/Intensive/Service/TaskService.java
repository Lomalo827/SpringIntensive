package com.Learn.Spring.Intensive.Service;


import com.Learn.Spring.Intensive.Entity.Priority;
import com.Learn.Spring.Intensive.Entity.Status;
import com.Learn.Spring.Intensive.Entity.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TaskService {

    private Map<Long, Task> task = new HashMap<Long,Task>(
            Map.of(1L, new Task(
                    1L,
                    1L,
                    Status.CREATED,
                    LocalDateTime.now(),
                    LocalDate.now().plusDays(5),
                    Priority.Low
            ),
                    2L,new Task(
                            2L,
                            2L,
                            Status.CREATED,
                            LocalDateTime.now(),
                            LocalDate.now().plusDays(3),
                            Priority.High
                    ),
                    3L,new Task(
                            3L,
                            3L,
                            Status.CREATED,
                            LocalDateTime.now(),
                            LocalDate.now().plusDays(1),
                            Priority.High
                    )
            )
    );

    public Task getTaskById(Long id){

        if (!task.containsKey(id)){
            throw new NoSuchElementException();
        }
        return task.get(id);
    }

    public List<Task> getAllTasks(){
        return task.values().stream().toList();
    }
}
