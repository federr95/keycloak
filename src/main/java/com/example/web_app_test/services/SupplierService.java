package com.example.web_app_test.services;

import com.example.web_app_test.entities.Supplier;
import com.example.web_app_test.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Optional<Supplier> getSupplierIdFromName(Long supplierId) {
        if(supplierRepository.existsById(supplierId))
            return supplierRepository.findById(supplierId);
        else
            return Optional.empty();
    }
}
