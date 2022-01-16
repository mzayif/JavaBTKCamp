package com.btkAkademi.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payments")
public class Payment extends BaseEntity  {


    @Column(name="total_price")
    private double totalPrice;
    @Column(name="is_paid")
    private boolean isPaid;
    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

}
