package com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests;

import com.btkAkademi.rentACar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequests {
    private LocalDate sendMaintenanceDate;
    private Car car;
}

