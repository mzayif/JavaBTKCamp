package com.btkAkademi.rentACar.business.requests.rentalRequests;

import com.btkAkademi.rentACar.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
    private int id;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private int rentedKilometer;
    private int returnedKilometer;
    private Customer customer;
}
