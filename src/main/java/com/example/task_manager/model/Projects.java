package com.example.task_manager.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private long projectId;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projects")
    private List<Department> projectDepartment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_project_id")
    private Employee projectManager;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Department> getProjectDepartment() {
        return projectDepartment;
    }

    public void setProjectDepartment(List<Department> projectDepartment) {
        this.projectDepartment = projectDepartment;
    }

    public Employee getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(Employee projectManager) {
        this.projectManager = projectManager;
    }

    public Projects(String name) {
        this.name = name;
    }

    public Projects() {
    }

    @PreRemove
    private void preRemove() {
        for (Department department : getProjectDepartment()) {
            department.setProjects(null);
        }
    }
}
