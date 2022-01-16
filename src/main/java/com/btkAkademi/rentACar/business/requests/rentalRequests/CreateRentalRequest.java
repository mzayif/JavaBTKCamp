package com.btkAkademi.rentACar.business.requests.rentalRequests;


import com.btkAkademi.rentACar.business.requests.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest implements IRequest {

    private LocalDate rentDate;
    private LocalDate returnDate;
    private int rentedKilometer;
    private int customerId;
    private int carId;
    private int picUpCityId;
    private int returnCityId;
}
