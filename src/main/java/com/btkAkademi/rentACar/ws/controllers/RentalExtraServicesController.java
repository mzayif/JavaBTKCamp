package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.RentalExtraServiceService;
import com.btkAkademi.rentACar.business.dtos.RentalExtraServiceListDto;
import com.btkAkademi.rentACar.business.requests.RentalExtraServiceRequests.CreateRentalExtraServiceRequest;
import com.btkAkademi.rentACar.business.requests.RentalExtraServiceRequests.UpdateRentalExtraServiceRequest;
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

    @GetMapping("getall")
    public ResponseEntity<DataResult<List<RentalExtraServiceListDto>>> getAll() {
        return ResponseEntity.ok(extraService.getAll());
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody CreateRentalExtraServiceRequest createRentalExtraServiceRequest) {
        var result = extraService.add(createRentalExtraServiceRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateRentalExtraServiceRequest updateRentalExtraServiceRequest) {
        var result = extraService.update(updateRentalExtraServiceRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }
}
