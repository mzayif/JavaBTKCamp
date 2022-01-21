package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.RentalExtraServiceService;
import com.btkAkademi.rentACar.business.dtos.RentalExtraServiceListDto;
import com.btkAkademi.rentACar.business.requests.rentalExtraServiceRequests.CreateRentalExtraServiceRequest;
import com.btkAkademi.rentACar.business.requests.rentalExtraServiceRequests.UpdateRentalExtraServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/rentalExtraServices")
public class RentalExtraServicesController {
    private final RentalExtraServiceService extraService;


    public RentalExtraServicesController(RentalExtraServiceService extraServiceService) {
        this.extraService = extraServiceService;
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid CreateRentalExtraServiceRequest createRentalExtraServiceRequest) {
        var result = extraService.add(createRentalExtraServiceRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateRentalExtraServiceRequest updateRentalExtraServiceRequest) {
        var result = extraService.update(updateRentalExtraServiceRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(int id) {
        var result = extraService.delete(id);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }


    @GetMapping("getall")
    public ResponseEntity<?> getAll() {
        var result = extraService.getAll();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @GetMapping("getByRentalId")
    public ResponseEntity<?> getByRentalId(int rentalId) {
        var result = extraService.getByRentalId(rentalId);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }
}
