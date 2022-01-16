package com.btkAkademi.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="car_damages")
public class CarDamage extends BaseEntity {

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;
}
