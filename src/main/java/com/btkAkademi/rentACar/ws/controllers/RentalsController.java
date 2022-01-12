package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/rentals")
public class RentalsController {
    private final RentalService rentalService;

    public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    @GetMapping("getall")
    public ResponseEntity<DataResult<List<RentalListDto>>> getAll() {
        return ResponseEntity.ok(rentalService.getAll());
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid CreateRentalRequest createRentalRequest) {
        var result = rentalService.add(createRentalRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) {
        var result = rentalService.update(updateRentalRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }
}
