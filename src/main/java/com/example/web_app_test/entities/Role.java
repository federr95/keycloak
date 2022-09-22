package com.example.web_app_test.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long roleId;

    private String roleType;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name =  "role_employee",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    List<Employee> employees = new ArrayList<>();

    public Role(String roleType) {
        this.roleType = roleType;
    }
}
