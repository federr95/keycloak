package com.example.web_app_test.controller;

import com.example.web_app_test.entities.Employee;
import com.example.web_app_test.entities.Role;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.web_app_test.services.EmployeeService;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("API/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/allEmployee")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public List<Employee> getEmployeeList(@RequestParam (name="access_token") String token) {

        String[] chunks = token.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String data = new String(decoder.decode(chunks[1]));

        JSONParser parser = new JSONParser();
        JSONObject json = null;

        try {
            json = (JSONObject) parser.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String surname = json.get("preferred_username").toString().split("\\.")[1];

        // take the userInfo from token to find companyName of the user
        List<String> company = employeeService.getEmployeeList().stream()
                .filter(x -> x.getSurname().contains(surname))
                .map(x -> x.getCompany().getCompanyName())
                .toList();

        if(json.get("realm_access").toString().contains("user")){
            return employeeService.getEmployeeList().stream()
                    .filter(x -> x.getCompany().getCompanyName().contains(company.get(0)))
                    .collect(Collectors.toList());
        } else {
            if(json.get("realm_access").toString().contains("super-user")){
                System.out.println("è super-user");
                return new ArrayList<>();
            } else {
                return employeeService.getEmployeeList();
            }
        }

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('user')")
    public Employee getEmployee(@PathVariable Long id){
        if(employeeService.getEmployee(id).isPresent())
            return employeeService.getEmployee(id).get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}/addRole")
    @PreAuthorize("hasRole('admin')")
    public boolean addRoleToEmployee(@PathVariable Long id, @RequestBody Role role){
        if(employeeService.getEmployee(id).isPresent()){
            employeeService.addRoleToEmployee(id, role);
            return true;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addEmployee")
    @PreAuthorize("hasRole('admin')")
    public  boolean addEmployee(@RequestBody Employee employee){
        if(employeeService.getEmployee(employee.getEmployeeId()).isEmpty())
            return employeeService.createEmployee(employee);
        else throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

}

