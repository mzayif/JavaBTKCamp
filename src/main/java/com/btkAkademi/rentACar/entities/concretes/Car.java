package com.btkAkademi.rentACar.entities.concretes;

import javax.persistence.*;

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
	
	@Column(name = "kilometer")
	private int kilometer;

//	@Column(name = "is_maintenance")
//	private boolean isMaintenance;

	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name="color_id")
	private Color color;


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
