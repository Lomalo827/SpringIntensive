package com.Learn.Spring.Intensive.Repository;

import com.Learn.Spring.Intensive.Entity.Status;
import com.Learn.Spring.Intensive.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

    @Query(value = "select COUNT(status) from task where assigned_user_id = :assigned_user_id and status = 'IN_PROGRESS'",nativeQuery = true)
    Long CountAssignedUsersWithStatusInProgress(@Param("assigned_user_id") Long assignedUserId);
}
