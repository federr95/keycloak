package com.example.web_app_test.services;

import com.example.web_app_test.entities.Employee;
import com.example.web_app_test.entities.Role;
import com.example.web_app_test.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.web_app_test.repositories.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<Employee> getEmployeeList(){
        return employeeRepository.findAll();
    }

    public boolean createEmployee(Employee employee){
        employeeRepository.saveAndFlush(employee);
        return true;
    }

    public Optional<Employee> getEmployee(Long employeeId) {
        if(employeeRepository.existsById(employeeId)){
            Optional<Employee> optionalClient = employeeRepository.findById(employeeId);
            return optionalClient;
        } else {
            return Optional.empty();
        }
    }

    public void addRoleToEmployee(Long id, Role role) {

        Employee employee = employeeRepository.getReferenceById(id);
        Role roleToBeAdd = roleRepository.getReferenceById(role.getRoleId());
        employee.addRole(roleToBeAdd);

        employeeRepository.saveAndFlush(employee);
        roleRepository.saveAndFlush(roleToBeAdd);

    }
}
