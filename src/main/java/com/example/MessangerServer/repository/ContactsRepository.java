package com.example.MessangerServer.repository;

import com.example.MessangerServer.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<Contact, Long> {

}
