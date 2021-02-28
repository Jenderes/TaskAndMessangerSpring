package com.example.MessangerServer.repository;

import com.example.MessangerServer.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestsRepository extends JpaRepository<Request,Long> {
}
