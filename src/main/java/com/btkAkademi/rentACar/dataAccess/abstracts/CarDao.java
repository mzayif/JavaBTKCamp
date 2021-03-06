package com.btkAkademi.rentACar.dataAccess.abstracts;

import com.btkAkademi.rentACar.business.requests.carRequests.AvailableCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.entities.concretes.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {

    Optional<List<Car>> findAllByCarSegmentTypeId(int carSegmentTypeId);
    Optional<Car> findByCarPlate(String carPlate);

    @Query("from Car c " +
            "left join c.carMaintenances m on m.returnMaintenanceDate is null " +
            "left join c.rentals r on r.returnDate is null or r.returnDate > ?1 " +
            "WHERE m.id is null and r.id is null")
    List<Car> getAvailableCars(LocalDate toDay);


    @Query("from Car c " +
            "left join c.carMaintenances m on m.returnMaintenanceDate is null " +
            "left join c.rentals r on r.returnDate is null or r.returnDate > ?1 " +
            "WHERE m.id is null and r.id is null and c.city.id=?2")
    List<Car> getAvailableCarsByCityId(LocalDate toDay, int cityId);


    @Query("from Car c " +
            "left join c.carMaintenances m on m.returnMaintenanceDate is null " +
            "left join c.rentals r on r.returnDate is null or r.returnDate > ?1 " +
            "WHERE m.id is null and r.id is null and c.city.id=?2 and c.id=?3")
    Car getAvailableCarsByCarId(LocalDate toDay, int cityId, int id);
}
