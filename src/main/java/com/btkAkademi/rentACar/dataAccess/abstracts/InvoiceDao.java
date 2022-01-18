package com.btkAkademi.rentACar.dataAccess.abstracts;

import com.btkAkademi.rentACar.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Integer> {
    Optional<Invoice> findAllByRentalId(int rentalId);
}
