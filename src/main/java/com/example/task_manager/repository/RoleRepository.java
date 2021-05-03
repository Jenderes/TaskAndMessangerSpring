package com.example.task_manager.repository;

import com.example.task_manager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName (String name);
}
