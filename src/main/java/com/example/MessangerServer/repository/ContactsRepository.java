package com.example.MessangerServer.repository;

import com.example.MessangerServer.model.Contact;
import com.example.MessangerServer.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactsRepository extends JpaRepository<Contact, Long> {
}
