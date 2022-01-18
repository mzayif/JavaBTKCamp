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
@Table(name = "invoice")
public class Invoice extends BaseEntity {
    @Column(name = "invoice_date")
    private LocalDate invoiceDate;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "total_discount", nullable = true)
    private double totalDiscount;

    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;


    @OneToMany(mappedBy = "invoice")
    private List<Payment> payments;
}
