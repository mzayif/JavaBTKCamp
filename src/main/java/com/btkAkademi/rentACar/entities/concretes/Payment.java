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
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="total_price")
    private double totalPrice;
    @Column(name="is_paid")
    private boolean isPaid;
    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

}
