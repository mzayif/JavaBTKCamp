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


    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody CreateCarDamagesRequest createCarDamagesRequest) {
        var result = carDamageService.add(createCarDamagesRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCarDamagesRequest updateCarDamagesRequest) {
        var result = carDamageService.update(updateCarDamagesRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(int id) {
        var result = carDamageService.delete(id);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }




    @GetMapping("getAll")
    public ResponseEntity<DataResult<List<CarDamageListDto>>> getAll() {
        var result = carDamageService.getAll();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @GetMapping("getAllByCarId")
    public ResponseEntity<DataResult<List<CarDamageListDto>>> getAllByCarId(int id) {
        var result = carDamageService.getAllByCarId(id);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }
}
