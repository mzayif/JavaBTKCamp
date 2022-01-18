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
@Table(name = "promotion_code")
public class PromotionCode  extends BaseEntity {
    @Column(name = "code_name")
    private String codeName;

    @Column(name = "discount_rate")
    private int discountRate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "promotionCode")
    private List<Rental> rentals;
}
