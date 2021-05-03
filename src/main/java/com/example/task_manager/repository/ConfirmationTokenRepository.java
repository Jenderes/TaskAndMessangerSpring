package com.example.task_manager.repository;

import com.example.task_manager.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
}
