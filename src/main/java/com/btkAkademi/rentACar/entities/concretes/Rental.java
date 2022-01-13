package com.btkAkademi.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    @JoinColumn(name = "pic_up_city_id")
    private City PicUpCity;

    @ManyToOne
    @JoinColumn(name = "return_up_city_id")
    private City ReturnCity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;



    @OneToMany(mappedBy = "rental")
    private List<RentalExtraService> rentalExtraServices;
}
