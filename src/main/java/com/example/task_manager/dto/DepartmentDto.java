package com.example.task_manager.dto;

import com.example.task_manager.model.Department;
import com.example.task_manager.model.Employee;

//TODO: переписать DTO private и не по get и set
public class DepartmentDto {
    long departmentId;
    String departmentName;
    long departmentManagerId;
    String managerFullName;


    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public long getDepartmentManagerId() {
        return departmentManagerId;
    }

    public void setDepartmentManagerId(long departmentManagerId) {
        this.departmentManagerId = departmentManagerId;
    }

    public String getManagerFullName() {
        return managerFullName;
    }

    public void setManagerFullName(String managerFullName) {
        this.managerFullName = managerFullName;
    }

    public static DepartmentDto convertToDTO (Department department){
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentId(department.getDepartmentId());
        departmentDto.setDepartmentName(department.getName());
        Employee employee = department.getDepartmentManager();
        departmentDto.setDepartmentManagerId(employee.getUserId());
        departmentDto.setManagerFullName(employee.getFirstName() + " " + employee.getLastName());
        return departmentDto;
    }
}
