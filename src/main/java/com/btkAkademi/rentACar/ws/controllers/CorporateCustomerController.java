package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.requests.IndividualCustomerRequest.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/CorporateCustomers")
public class CorporateCustomerController {

    private final CorporateCustomerService corporateCustomerService;

    public CorporateCustomerController(CorporateCustomerService corporateCustomerService) {
        this.corporateCustomerService = corporateCustomerService;
    }


    @GetMapping("getall")
    public ResponseEntity<DataResult<List<CorporateCustomerDao>>> getAll() {
        var result = corporateCustomerService.getAll();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PostMapping("add")
    public ResponseEntity<?> getAll(@RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        var result = corporateCustomerService.add(createCorporateCustomerRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

//    @PostMapping("update")
//    public ResponseEntity<?> udate(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
//
//        var result = individualCustomerService.updateCar(updateCarRequest);
//        return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
//    }
}
