package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.CarDamageService;
import com.btkAkademi.rentACar.business.dtos.CarDamageListDto;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.CreateCarDamagesRequest;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.UpdateCarDamagesRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/carDamages")
public class CarDamagesController {
    private final CarDamageService carDamageService;


    public CarDamagesController(CarDamageService carService) {
        super();
        this.carDamageService = carService;
    }

    @GetMapping("getall")
    public ResponseEntity<DataResult<List<CarDamageListDto>>> getAll() {
        return ResponseEntity.ok(carDamageService.getAll());
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody CreateCarDamagesRequest createCarDamagesRequest) {
        var result = carDamageService.add(createCarDamagesRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCarDamagesRequest updateCarDamagesRequest) {

        var result = carDamageService.update(updateCarDamagesRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }
}
