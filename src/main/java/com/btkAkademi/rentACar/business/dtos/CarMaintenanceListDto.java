package com.btkAkademi.rentACar.business.dtos;

import com.btkAkademi.rentACar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceListDto implements IDto {
    private int id;
    private LocalDate sendMaintenanceDate;
    private LocalDate returnMaintenanceDate;
    private Car car;
}
