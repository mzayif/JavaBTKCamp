package com.btkAkademi.rentACar.business.dtos;

import com.btkAkademi.rentACar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDamageListDto implements IDto {
    private int id;
    private String description;
    private int carId;
    private String carPlate;
    private String brandName;
    private String colorName;
    private String segmentName;
}
