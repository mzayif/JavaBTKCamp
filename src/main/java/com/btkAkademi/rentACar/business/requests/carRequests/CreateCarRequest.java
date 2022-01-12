package com.btkAkademi.rentACar.business.requests.carRequests;

import com.btkAkademi.rentACar.business.requests.IRequest;
import com.btkAkademi.rentACar.entities.concretes.Brand;
import com.btkAkademi.rentACar.entities.concretes.Color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest implements IRequest{
	
	private double dailyPrice;
	private int modelYear;
	private String description;
	private int kilometer;
	private int findexScore;
	
	private Brand brand;
	private Color color;
}
