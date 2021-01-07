package com.example.MessangerServer.repository;

import com.example.MessangerServer.model.GroupMessages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMessagesRepository extends JpaRepository<GroupMessages, Long> {
}
