package com.example.MessangerServer.repository;

import com.example.MessangerServer.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks,Long> {
}
