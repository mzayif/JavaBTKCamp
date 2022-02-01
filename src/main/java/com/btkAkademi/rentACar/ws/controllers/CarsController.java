package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.abstracts.CreditCardService;
import com.btkAkademi.rentACar.business.requests.bankRequests.CreateCreditCardRequests;
import com.btkAkademi.rentACar.business.requests.bankRequests.UpdateCreditCardRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;

@RestController
@RequestMapping("api/cars")
@CrossOrigin
public class CarsController {
    private final CarService carService;


    public CarsController(CarService carService) {
        super();
        this.carService = carService;
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid CreateCarRequest carCreateDto) {
        var result = carService.add(carCreateDto);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
        var result = carService.update(updateCarRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(int id) {
        var result = carService.delete(id);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }


    @GetMapping("")
    public ResponseEntity<?> getOne(int id) {
        var result = carService.getOne(id);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @GetMapping("getall")
    public ResponseEntity<?> getAll() {
        var result = carService.getAll();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @GetMapping("getAllByPage")
    public ResponseEntity<?> getAllByPage(int pageNo, int pageSize) {
        var result = carService.getPageable(pageNo == 0 ? 1 : pageNo, pageSize == 0 ? 10 : pageSize);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @GetMapping("getAvailableCarForRent")
    public ResponseEntity<?> getAvailableCarForRent() {
        var result = carService.getAvailableCarForRent();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }
}
