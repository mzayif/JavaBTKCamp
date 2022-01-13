package com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests;

import com.btkAkademi.rentACar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequests {
    private int id;
    private LocalDate returnMaintenanceDate;
}

