package com.example.MessangerServer.repository;

import com.example.MessangerServer.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Group,Long> {

}
