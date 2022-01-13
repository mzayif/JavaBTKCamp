package com.btkAkademi.rentACar.dataAccess.abstracts;

import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;
import com.btkAkademi.rentACar.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentalDao extends JpaRepository<Rental, Integer> {

    @Query("from Rental r  where r.returnDate is null or r.returnDate>=?1")
    Optional<List<Rental>> getOnRentCars(LocalDate date);

    Optional<List<Rental>> findAllByCarId(int CarId);
}

