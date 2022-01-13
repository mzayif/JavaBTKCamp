package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CarDamageService;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.dtos.CarDamageListDto;
import com.btkAkademi.rentACar.business.requests.AdditionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.AdditionalServiceRequests.UpdateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.CarDamageRequests.CreateCarDamagesRequest;
import com.btkAkademi.rentACar.business.requests.CarDamageRequests.UpdateCarDamagesRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/additionalServices")
public class AdditionalServicesController {

    private final AdditionalServiceService additionalService;


    public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
        this.additionalService = additionalServiceService;
    }

    @GetMapping("getall")
    public ResponseEntity<DataResult<List<AdditionalServiceListDto>>> getAll() {
        return ResponseEntity.ok(additionalService.getAll());
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody CreateAdditionalServiceRequest additionalServiceRequest) {
        var result = additionalService.add(additionalServiceRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
        var result = additionalService.update(updateAdditionalServiceRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }
}
