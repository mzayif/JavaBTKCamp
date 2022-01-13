package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.CustomerRequest.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/IndividualCustomers")
public class IndividualCustomerController {

    private final IndividualCustomerService individualCustomerService;

    public IndividualCustomerController(IndividualCustomerService individualCustomerService) {
        this.individualCustomerService = individualCustomerService;
    }


    @GetMapping("getall")
    public ResponseEntity<DataResult<List<IndividualCustomerListDto>>> getAll() {
        var result = individualCustomerService.getAll();

        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PostMapping("add")
    public ResponseEntity<?> getAll(@RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        var result = individualCustomerService.add(createIndividualCustomerRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

//    @PostMapping("update")
//    public ResponseEntity<?> udate(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
//
//        var result = individualCustomerService.updateCar(updateCarRequest);
//        return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
//    }
}

