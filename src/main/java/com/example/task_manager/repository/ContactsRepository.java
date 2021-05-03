package com.example.task_manager.repository;

import com.example.task_manager.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<Contact, Long> {
}
