package com.btkAkademi.rentACar.dataAccess.abstracts;

import com.btkAkademi.rentACar.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.entities.concretes.Car;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {

}
