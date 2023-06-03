package com.kohn.apitodo.repositories;

import com.kohn.apitodo.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByUserId(Long userId, Pageable pageable);
}
