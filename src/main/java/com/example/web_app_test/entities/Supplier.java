package com.example.web_app_test.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierId;

    private String supplierName;

    @ManyToMany
    @JoinTable(
            name = "supplier_company",
            joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")

    )
    List<Company> companyList = new ArrayList<>();

    public Supplier(String supplierName) {
        this.supplierName = supplierName;
    }

}
