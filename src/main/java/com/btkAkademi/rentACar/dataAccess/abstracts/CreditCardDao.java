package com.btkAkademi.rentACar.dataAccess.abstracts;

import com.btkAkademi.rentACar.entities.concretes.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardDao extends JpaRepository<CreditCard, Integer> {
    Optional<CreditCard> findByCardNumberAndCustomerId(String cardNumber, int CustomerId);

    Optional<List<CreditCard>> findAllByCustomerId(int customerId);
}
