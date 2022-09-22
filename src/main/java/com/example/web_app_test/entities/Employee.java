package com.example.web_app_test.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    private Long employeeId;
    private String name;
    private String surname;

    @ManyToOne
    @JoinColumn(name="company_id")
    public Company company;

    @ManyToMany(mappedBy = "employees")
    public List<Role> roles = new ArrayList<>();

    public void addRole(Role role){
        role.getEmployees().add(this);
        roles.add(role);
    }

    public Employee(Long employeeId, String name, String surname) {
        this.employeeId = employeeId;
        this.name = name;
        this.surname = surname;
    }

    public void setCompany(Company company){
        this.company = company;
        company.companyEmployees.add(this);
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
