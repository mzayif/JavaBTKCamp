package com.btkAkademi.rentACar.dataAccess.abstracts;

import com.btkAkademi.rentACar.entities.concretes.Brand;
import com.btkAkademi.rentACar.entities.concretes.CarDamage;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarMaintenanceDao extends JpaRepository<CarMaintenance, Integer> {
    @Query("from CarMaintenance b  where b.returnMaintenanceDate is null and b.car.id = ?1")
    Optional<List<CarMaintenance>> checkCarMaintenance(int carId);

    @Query("from CarMaintenance b  where b.returnMaintenanceDate is null")
    Optional<List<CarMaintenance>> getAllInActiveMaintenance();

    CarMaintenance findByCarIdAndAndReturnMaintenanceDateIsNull(int CarId);

    Optional<List<CarMaintenance>> findAllByCarId(int carId);
}

