package com.example.MessangerServer.repository;

import com.example.MessangerServer.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Groups,Long> {
}
