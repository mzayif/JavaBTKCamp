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
public class Rental extends BaseEntity  {


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

    @ManyToOne
    @JoinColumn(name = "promotion_code_id")
    private PromotionCode promotionCode;



    @OneToMany(mappedBy = "rental")
    private List<Payment> payments;

    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @OneToMany(mappedBy = "rental")
    private List<RentalExtraService> rentalExtraServices;
}
