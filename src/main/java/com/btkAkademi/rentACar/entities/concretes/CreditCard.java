package com.btkAkademi.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "credit_cards")
public class CreditCard extends BaseEntity {

    @Column(name = "card_name")
    private String cardName;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "last_date")
    private String lastDate;
    @Column(name = "cvv")
    private String cvv;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
