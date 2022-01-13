package com.btkAkademi.rentACar.business.requests.rentalRequests;


import com.btkAkademi.rentACar.business.requests.IRequest;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.CacheRequest;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest implements IRequest {

    private LocalDate rentDate;
    private LocalDate returnDate;
    private int rentedKilometer;
    private int returnedKilometer;
    private Customer customer;
    private Car car;
}
