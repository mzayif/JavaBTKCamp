package com.btkAkademi.rentACar.dataAccess.abstracts;

import com.btkAkademi.rentACar.entities.concretes.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {
    Optional<List<Payment>> findAllByRentalId(int rentalId);

//    @Query("SELECT new com.NewPojo(SUM(m.totalDays)) FROM Payment m")
//    NewPojo getTotalPrice();
}
