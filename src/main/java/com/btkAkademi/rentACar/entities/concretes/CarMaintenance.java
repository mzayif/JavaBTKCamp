package com.btkAkademi.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="car_maintenances")
public class CarMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "rent_maintenance_date")
    private LocalDate sendMaintenanceDate;
    @Column(name = "return_maintenance_date")
    private LocalDate returnMaintenanceDate;


    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;
}
