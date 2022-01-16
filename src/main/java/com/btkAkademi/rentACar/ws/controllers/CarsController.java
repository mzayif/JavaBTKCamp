package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

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

    @GetMapping("/getAllByPage")
    public ResponseEntity<DataResult<List<CarListDto>>> getAllByPage(int pageNo, int pageSize) {
        return ResponseEntity.ok(carService.getPageable(pageNo == 0 ? 1 : pageNo,  pageSize == 0 ? 10 : pageSize));
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody CreateCarRequest carCreateDto) {
        return ResponseEntity.ok(carService.add(carCreateDto));
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {

        var result = carService.update(updateCarRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }
}
