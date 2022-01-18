package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.customerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.customerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/IndividualCustomers")
public class IndividualCustomerController {

    private final IndividualCustomerService individualCustomerService;

    public IndividualCustomerController(IndividualCustomerService individualCustomerService) {
        this.individualCustomerService = individualCustomerService;
    }
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        var result = individualCustomerService.add(createIndividualCustomerRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateIndividualCustomerRequest individualCustomerRequest) {
        var result = individualCustomerService.update(individualCustomerRequest);
        return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(int id) {
        var result = individualCustomerService.delete(id);
        return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
    }


    @GetMapping("getall")
    public ResponseEntity<DataResult<List<IndividualCustomerListDto>>> getAll() {
        var result = individualCustomerService.getAll();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @GetMapping("getAllByPage")
    public ResponseEntity<?> getAllByPage(int pageNo, int pageSize) {
        var result = individualCustomerService.getPageable(pageNo == 0 ? 1 : pageNo, pageSize == 0 ? 10 : pageSize);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

}

