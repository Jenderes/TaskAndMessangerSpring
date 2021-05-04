package com.example.task_manager.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private long departmentId;

    private String name;

    private WorkVariant workDepartment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_department_id")
    private Employee departmentManager;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<Employee>  EmployeeDepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Projects projects;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(Employee departmentManager) {
        this.departmentManager = departmentManager;
    }

    public List<Employee> getEmployeeDepartment() {
        return EmployeeDepartment;
    }

    public void setEmployeeDepartment(List<Employee> employeeDepartment) {
        EmployeeDepartment = employeeDepartment;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public WorkVariant getWorkDepartment() {
        return workDepartment;
    }

    public void setWorkDepartment(WorkVariant workDepartment) {
        this.workDepartment = workDepartment;
    }

    @PreRemove
    private void preRemove() {
        for (Employee employee : getEmployeeDepartment()) {
            employee.setDepartment(null);
        }
    }
}
