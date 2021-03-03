package com.example.MessangerServer.repository;

import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Group,Long> {
    // Поиск группы по Имени
    Group findByGroupName(String groupName);

    // Поиск группы по id
    Group findByGroupId(Long groupId);
}
