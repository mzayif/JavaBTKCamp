package com.btkAkademi.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="additional_services")
public class AdditionalService extends BaseEntity {

    @Column(name="service_name")
    private String serviceName;

    @Column(name="service_price")
    private double servicePrice;
}
