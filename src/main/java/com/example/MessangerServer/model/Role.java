package com.example.MessangerServer.model;

import javax.persistence.Id;
import javax.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role  implements GrantedAuthority {
    @Id
    private Long id;
    private String Name;
    @Transient
    private Set<Employees> employees;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        Name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Set<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employees> employees) {
        this.employees = employees;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
