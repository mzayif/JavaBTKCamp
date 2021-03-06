package com.btkAkademi.rentACar.entities.concretes;

import javax.persistence.*;

import com.btkAkademi.rentACar.business.enums.CarType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cars")
public class Car extends BaseEntity {

	@Column(name = "daily_price")
	private double dailyPrice;
	
	@Column(name = "model_year")
	private int modelYear;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "findex_score")
	private int findexScore;

	@Column(name = "min_year")
	private int minYear;
	
	@Column(name = "kilometer")
	private int kilometer;

	@Column(name = "car_plate")
	private String carPlate;


//	@Column(name = "is_maintenance")
//	private boolean isMaintenance;

	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;

	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;

	@ManyToOne
	@JoinColumn(name="color_id")
	private Color color;

	@ManyToOne
	@JoinColumn(name="car_segment_type_id")
	private CarSegmentType carSegmentType;

	@JsonIgnore
	@OneToMany(mappedBy = "car")
	private List<CarMaintenance> carMaintenances;
	@JsonIgnore
	@OneToMany(mappedBy = "car")
	private List<Rental> rentals;
	@JsonIgnore
	@OneToMany(mappedBy = "car")
	private List<CarDamage> carDamages;
}
