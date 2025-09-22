package com.Learn.Spring.Intensive.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private Long taskId;
    private Long creatorId;
    private Status status;
    private LocalDateTime createDateTime;
    private LocalDate deadlineDate;
    private Priority priority;

    public Task(Long taskId, Long creatorId, Status status, LocalDateTime createDateTime, LocalDate deadlineDate, Priority priority) {
        this.taskId = taskId;
        this.creatorId = creatorId;
        this.status = status;
        this.createDateTime = createDateTime;
        this.deadlineDate = deadlineDate;
        this.priority = priority;
    }

    public Long getId() {
        return taskId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public Priority getPriority() {
        return priority;
    }
}


