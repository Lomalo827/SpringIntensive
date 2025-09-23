package com.Learn.Spring.Intensive.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "task")
@Entity
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "createDateTime")
    private LocalDateTime createDateTime;

    @Column(name = "deadlineDate")
    private LocalDate deadlineDate;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    public TaskEntity() {
    }

    public TaskEntity(Long taskId, Long creatorId, Status status, LocalDateTime createDateTime, LocalDate deadlineDate, Priority priority) {
        this.taskId = taskId;
        this.creatorId = creatorId;
        this.status = status;
        this.createDateTime = createDateTime;
        this.deadlineDate = deadlineDate;
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Long getCreatorId() {
        return creatorId;
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

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
