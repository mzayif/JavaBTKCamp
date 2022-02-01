package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/additionalServices")
@CrossOrigin
public class AdditionalServicesController {

    private final AdditionalServiceService additionalService;


    public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
        this.additionalService = additionalServiceService;
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid CreateAdditionalServiceRequest additionalServiceRequest) {
        var result = additionalService.add(additionalServiceRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
        var result = additionalService.update(updateAdditionalServiceRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(int id) {
        var result = additionalService.delete(id);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }


    @GetMapping("getall")
    public ResponseEntity<DataResult<List<AdditionalServiceListDto>>> getAll() {
        return ResponseEntity.ok(additionalService.getAll());
    }
}
