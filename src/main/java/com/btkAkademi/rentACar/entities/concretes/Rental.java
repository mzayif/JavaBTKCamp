package com.btkAkademi.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "rent_date")
    private LocalDate rentDate;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @Column(name = "rented_kilometer")
    private int rentedKilometer;
    @Column(name = "return_kilometer")
    private int returnedKilometer;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
