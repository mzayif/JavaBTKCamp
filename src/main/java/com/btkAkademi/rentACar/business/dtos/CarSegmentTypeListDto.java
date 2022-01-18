package com.btkAkademi.rentACar.business.dtos;

import com.btkAkademi.rentACar.business.enums.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarSegmentTypeListDto implements IDto {
    private int id;
    private String segmentName;
    private CarType carType;
}
