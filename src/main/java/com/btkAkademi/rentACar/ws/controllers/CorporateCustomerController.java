package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
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
@CrossOrigin
public class CorporateCustomerController {

    private final CorporateCustomerService corporateCustomerService;

    public CorporateCustomerController(CorporateCustomerService corporateCustomerService) {
        this.corporateCustomerService = corporateCustomerService;
    }


    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        var result = corporateCustomerService.add(createCorporateCustomerRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
        var result = corporateCustomerService.update(updateCorporateCustomerRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(int id) {
        var result = corporateCustomerService.delete(id);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }



    @GetMapping("getall")
    public ResponseEntity<?> getAll() {
        var result = corporateCustomerService.getAll();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @GetMapping("getAllByPage")
    public ResponseEntity<?> getAllByPage(int pageNo, int pageSize) {
        var result = corporateCustomerService.getPageable(pageNo == 0 ? 1 : pageNo, pageSize == 0 ? 10 : pageSize);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }
}
