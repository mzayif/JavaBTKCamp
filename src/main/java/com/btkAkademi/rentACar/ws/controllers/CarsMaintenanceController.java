package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests.CreateCarMaintenanceRequests;
import com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests.UpdateCarMaintenanceRequests;
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

    @GetMapping("getall")
    public ResponseEntity<DataResult<List<CarMaintenanceListDto>>> getAll() {
        return ResponseEntity.ok(carMaintenanceService.getAll());
    }

    @PostMapping("add")
    public ResponseEntity<?> getAll(@RequestBody CreateCarMaintenanceRequests createCarMaintenanceRequests) {
        return ResponseEntity.ok(carMaintenanceService.add(createCarMaintenanceRequests));
    }

    @PostMapping("update")
    public ResponseEntity<?> udate(@RequestBody @Valid UpdateCarMaintenanceRequests updateCarMaintenanceRequests) {

        var result = carMaintenanceService.update(updateCarMaintenanceRequests);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }
}
