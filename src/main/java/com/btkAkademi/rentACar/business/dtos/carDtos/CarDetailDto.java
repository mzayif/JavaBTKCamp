package com.btkAkademi.rentACar.business.dtos.carDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetailDto {
    private int id;
    private int modelYear;
    private String description;
    private int findexScore;
    private int minYear;
    private String brandName;
    private String colorName;
    private String segmentName;
    private int kilometer;
    private double dailyPrice;
}
