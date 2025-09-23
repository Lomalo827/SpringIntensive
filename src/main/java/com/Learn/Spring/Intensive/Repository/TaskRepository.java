package com.Learn.Spring.Intensive.Repository;

import com.Learn.Spring.Intensive.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {
}
