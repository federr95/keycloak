package com.example.web_app_test;

import com.example.web_app_test.entities.Company;
import com.example.web_app_test.entities.Employee;
import com.example.web_app_test.entities.Role;
import com.example.web_app_test.entities.Supplier;
import com.example.web_app_test.repositories.CompanyRepository;
import com.example.web_app_test.repositories.EmployeeRepository;
import com.example.web_app_test.repositories.RoleRepository;
import com.example.web_app_test.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class WebAppTestApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	CommandLineRunner runner(EmployeeRepository employeeRepository, CompanyRepository companyRepository, SupplierRepository supplierRepository,
								RoleRepository roleRepository){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				/*Company company = new Company("RossiCompany");
				Company company1 = new Company("ViolaCompany");
				Company company2 = new Company("NeroCompany");
				companyRepository.saveAndFlush(company);
				companyRepository.saveAndFlush(company1);
				companyRepository.saveAndFlush(company2);

				Employee employee = new Employee(101L, "mario", "bianchi");
				Employee employee1 = new Employee(102L, "francesco", "verdi");
				Employee employee2 = new Employee(103L, "giovanni", "viola");
				Employee employee3 = new Employee(104L, "luca", "rosa");
				Employee employee4 = new Employee(105L, "franco", "rossi");

				employeeRepository.saveAndFlush(employee);
				employeeRepository.saveAndFlush(employee1);
				employeeRepository.saveAndFlush(employee2);
				employeeRepository.saveAndFlush(employee3);
				employeeRepository.saveAndFlush(employee4);

				Role userRole = new Role("user");
				Role adminRole = new Role("admin");
				Role superUserRole = new Role("superUser");
				roleRepository.saveAndFlush(userRole);
				roleRepository.saveAndFlush(adminRole);
				roleRepository.saveAndFlush(superUserRole);


				/*Employee employee = new Employee(100L, "franco", "rossi");
				Employee employee2 = new Employee(101L, "mario", "bianchi");
				Employee employee3 = new Employee(102L, "giorgio", "viola");
				employeeRepository.save(employee);
				employeeRepository.save(employee2);
				employeeRepository.save(employee3);

				List<String> companyNameList = companyRepository.findAll().stream()
						.map(Company::getCompanyName).toList();

				if(!companyNameList.contains("RossiCompany")){
					Company company = new Company("RossiCompany");
					companyRepository.save(company);1
					employee.setCompany(company);
					employeeRepository.save(employee);
					employee3.setCompany(company);
					employeeRepository.save(employee3);
				}

				if(!companyNameList.contains("ViolaCompany")){
					Company company = new Company("ViolaCompany");
					companyRepository.save(company);
					employee2.setCompany(company);
					employeeRepository.save(employee2);
				}

				List<String> supplierList = supplierRepository.findAll().stream()
						.map(Supplier::getSupplierName).toList();

				if(!supplierList.contains("GearSupplier")){
					Supplier supplier = new Supplier("GearSupplier");
					supplierRepository.save(supplier);
				}

				List<String> roleList = roleRepository.findAll().stream()
						.map((Role::getRoleType)).toList();

				/*if(!roleList.contains("user")) {
					Role role = new Role("user");
					employee.addRole(role);
					roleRepository.save(role);
					employeeRepository.save(employee);
					employee2.addRole(role);
					employeeRepository.save(employee2);
					roleRepository.save(role);
				}

				if(!roleList.contains("admin")) {
					Role role = new Role("admin");
					employee3.addRole(role);
					roleRepository.save(role);
					employeeRepository.save(employee3);
				}

				if(!roleList.contains("super-user")) {
					Role role = new Role("super-user");
					roleRepository.save(role);
				}*/

			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(WebAppTestApplication.class, args);
	}

}
