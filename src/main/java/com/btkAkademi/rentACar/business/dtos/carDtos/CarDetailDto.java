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
    private String carPlate;
    private String description;
    private int findexScore;
    private int minYear;
    private int brandId;
    private String brandName;
    private int colorId;
    private String colorName;
    private int carSegmentTypeId;
    private String segmentName;
    private int kilometer;
    private double dailyPrice;
}
