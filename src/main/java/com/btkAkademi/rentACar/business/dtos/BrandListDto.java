package com.btkAkademi.rentACar.business.dtos;

import com.btkAkademi.rentACar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandListDto implements IDto{
	private int id;
	private String name;
}
