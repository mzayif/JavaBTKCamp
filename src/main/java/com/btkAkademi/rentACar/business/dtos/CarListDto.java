package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListDto implements IDto{
	
	private int id;
	private int modelYear;
	private String description;
	private int kilometer;
	private double dailyPrice;
	private String carPlate;
	private String brandName;
	private String colorName;
	private String segmentName;
}
