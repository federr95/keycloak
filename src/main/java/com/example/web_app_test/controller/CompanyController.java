package com.example.web_app_test.controller;

import com.example.web_app_test.dtos.CompanyDTO;
import com.example.web_app_test.entities.Employee;
import com.example.web_app_test.entities.Company;
import com.example.web_app_test.entities.Supplier;
import com.example.web_app_test.services.EmployeeService;
import com.example.web_app_test.services.CompanyService;
import com.example.web_app_test.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("API/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/allCompanies")
    public List<CompanyDTO> getCompanies(){
        List<CompanyDTO> companyDTOList = new ArrayList<>();
        for(Company company : companyService.getCompanies())
            companyDTOList.add(modelMapper.map(company, CompanyDTO.class));
        return companyDTOList;
    }

    @PostMapping("/addCompany")
    public boolean addCompany(@RequestBody Company company){
        if(companyService.getCompanyId(company.getCompanyName()).isEmpty())
            return companyService.addCompany(company);
        else
            throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    @PostMapping("/{companyName}/enrollOne")
    public void enrollClient(@RequestBody Employee employee, @PathVariable String companyName){
        // TODO controllo che non sia già presente
        if(companyService.getCompanyId(companyName).isPresent()){
            Long companyId  = companyService.getCompanyId(companyName).get();
            if(employeeService.getEmployee(employee.getEmployeeId()).isPresent())
                companyService.addEmployeeToCompany(companyId, employee);
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{companyName}/enrollSupplier")
    public void enrollSupplier(@RequestBody Supplier supplier, @PathVariable String companyName){
        if(companyService.getCompanyId(companyName).isPresent()){
            Long companyId = companyService.getCompanyId(companyName).get();
            if(supplierService.getSupplierIdFromName(supplier.getSupplierId()).isPresent()){
                companyService.addSupplierToCompany(companyId, supplier);
            } else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
