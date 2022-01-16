package com.btkAkademi.rentACar.entities.concretes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rental_extra_service")
public class RentalExtraService extends BaseEntity  {

    @Column(name="service_price")
    private double servicePrice;

    @ManyToOne
    @JoinColumn(name="additional_service_id")
    private AdditionalService additionalService;

    @ManyToOne
    @JoinColumn(name="rental_id")
    private Rental rental;
}
