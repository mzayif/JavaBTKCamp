package com.btkAkademi.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "city")
public class City extends BaseEntity {

    @Column(name = "city_code")
    private int cityCode;

    @Column(name = "name")
    private String name;
}
