package com.example.web_app_test.services;

import com.example.web_app_test.entities.Employee;
import com.example.web_app_test.entities.Company;
import com.example.web_app_test.entities.Supplier;
import com.example.web_app_test.repositories.EmployeeRepository;
import com.example.web_app_test.repositories.CompanyRepository;
import com.example.web_app_test.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SupplierRepository supplierRepository;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public boolean addCompany(Company company) {
        companyRepository.saveAndFlush(company);
        return true;
    }

    public Optional<Long> getCompanyId(String companyName) {
        return companyRepository.findAll().stream()
                .filter(x -> x.getCompanyName().contains(companyName))
                .map(x -> x.getCompanyId())
                .findFirst();
    }

    public void addEmployeeToCompany(Long companyId, Employee employee) {

        Company company = companyRepository.getReferenceById(companyId);
        Employee employeeToBeGot = employeeRepository.getReferenceById(employee.getEmployeeId());
        employeeToBeGot.setCompany(company);

        //employeeRepository.save(employee);
        companyRepository.save(company);

        //System.out.println();

    }

    public void addSupplierToCompany(Long companyId, Supplier supplier) {

        Company company = companyRepository.getReferenceById(companyId);
        company.addSupplier(supplier);

        companyRepository.saveAndFlush(company);
        supplierRepository.saveAndFlush(supplier);
    }
}
