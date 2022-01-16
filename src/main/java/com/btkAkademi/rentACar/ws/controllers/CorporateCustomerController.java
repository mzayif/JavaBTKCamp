package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.customerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.customerRequests.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/CorporateCustomers")
public class CorporateCustomerController {

    private final CorporateCustomerService corporateCustomerService;

    public CorporateCustomerController(CorporateCustomerService corporateCustomerService) {
        this.corporateCustomerService = corporateCustomerService;
    }


    @GetMapping("getall")
    public ResponseEntity<DataResult<List<CorporateCustomerListDto>>> getAll() {
        var result = corporateCustomerService.getAll();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        var result = corporateCustomerService.add(createCorporateCustomerRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {

        var result = corporateCustomerService.update(updateCorporateCustomerRequest);
        return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
    }
}
