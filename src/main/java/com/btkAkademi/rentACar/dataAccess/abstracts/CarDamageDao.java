package com.btkAkademi.rentACar.dataAccess.abstracts;

import com.btkAkademi.rentACar.entities.concretes.CarDamage;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarDamageDao extends JpaRepository<CarDamage, Integer> {
//    @Query("from CarMaintenance b  where b.returnMaintenanceDate is null and b.car.id = ?1")
//    Optional<List<CarMaintenance>> checkCarMaintenance(int carId);

    Optional<List<CarMaintenance>> findByCarId(int CarId);
}
