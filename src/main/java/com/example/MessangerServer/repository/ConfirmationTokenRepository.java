package com.example.MessangerServer.repository;

import com.example.MessangerServer.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
}
