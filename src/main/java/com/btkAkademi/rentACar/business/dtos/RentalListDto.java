package com.btkAkademi.rentACar.business.dtos;

import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalListDto implements IDto {
    private int id;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private int rentedKilometer;
    private int returnedKilometer;


    private Customer customer;
    private Car car;
}


