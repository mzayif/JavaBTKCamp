package com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests;

import com.btkAkademi.rentACar.business.requests.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequests implements IRequest {

    private LocalDate sendMaintenanceDate;
    private int carId;
}

