package com.btkAkademi.rentACar.business.requests.carMaintenanseRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequests {
    private int id;
    private LocalDate sendMaintenanceDate;
    private LocalDate returnMaintenanceDate;
}

