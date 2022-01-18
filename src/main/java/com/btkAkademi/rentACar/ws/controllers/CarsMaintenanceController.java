package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenanseRequests.CreateCarMaintenanceRequests;
import com.btkAkademi.rentACar.business.requests.carMaintenanseRequests.UpdateCarMaintenanceRequests;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/carMaintenances")
public class CarsMaintenanceController {
    private final CarMaintenanceService carMaintenanceService;


    public CarsMaintenanceController(CarMaintenanceService carService) {
        super();
        this.carMaintenanceService = carService;
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody CreateCarMaintenanceRequests createCarMaintenanceRequests) {
        return ResponseEntity.ok(carMaintenanceService.add(createCarMaintenanceRequests));
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCarMaintenanceRequests updateCarMaintenanceRequests) {
        var result = carMaintenanceService.update(updateCarMaintenanceRequests);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(int id) {
        var result = carMaintenanceService.delete(id);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }





    @GetMapping("getall")
    public ResponseEntity<DataResult<List<CarMaintenanceListDto>>> getAll() {
        var result = carMaintenanceService.getAll();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @GetMapping("getAllInActiveMaintenance")
    public ResponseEntity<DataResult<List<CarMaintenanceListDto>>> getAllInActiveMaintenance() {
        var result = carMaintenanceService.getAllInActiveMaintenance();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @GetMapping("getAllByCarId")
    public ResponseEntity<DataResult<List<CarMaintenanceListDto>>> getAllByCarId(int id) {
        var result = carMaintenanceService.getAllByCarId(id);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }
}
