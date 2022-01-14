package com.btkAkademi.rentACar.dataAccess.abstracts;

import com.btkAkademi.rentACar.entities.concretes.Brand;
import com.btkAkademi.rentACar.entities.concretes.RentalExtraService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalExtraServiceDao extends JpaRepository<RentalExtraService, Integer> {
    Optional<List<RentalExtraService>> findAllByRentalId(int rentalId);
}
