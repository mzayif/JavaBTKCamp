package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests.CreateCarMaintenanceRequests;
import com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests.UpdateCarMaintenanceRequests;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;

@RestController
@RequestMapping("api/cars")
public class CarsController {
	private final CarService carService;


	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	}

	@GetMapping("getall")
	public ResponseEntity<DataResult<List<CarListDto>>> getAll() {
		return ResponseEntity.ok(carService.getAll());
	}
	
	@PostMapping("add")
	public ResponseEntity<?> getAll(@RequestBody CreateCarRequest carCreateDto) {
		return ResponseEntity.ok(carService.add(carCreateDto));
	}

	@PostMapping("update")
	public ResponseEntity<?> udate(@RequestBody @Valid UpdateCarRequest updateCarRequest) {

		var result = carService.update(updateCarRequest);
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
}
