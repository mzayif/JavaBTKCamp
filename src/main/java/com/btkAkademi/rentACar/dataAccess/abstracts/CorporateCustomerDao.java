package com.btkAkademi.rentACar.dataAccess.abstracts;

import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer, Integer> {
    Optional<CorporateCustomer> findByEmail(String email);
    Optional<CorporateCustomer> findByCompanyName(String companyName);
}
