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
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="company_id")
    private Long companyId;
    private String companyName;

    @JsonIgnore
    @OneToMany(mappedBy="company")
    public List<Employee> companyEmployees = new ArrayList<>();

    @ManyToMany(mappedBy="companyList")
    public List<Supplier> supplierList = new ArrayList<>();

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public void addSupplier(Supplier supplier){
        supplier.getCompanyList().add(this);
        supplierList.add(supplier);
    }
}
