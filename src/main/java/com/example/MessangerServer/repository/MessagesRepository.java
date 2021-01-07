package com.example.MessangerServer.repository;

import com.example.MessangerServer.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages,Long> {
}
