package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.requests.CityRequests.CreateCityRequest;
import com.btkAkademi.rentACar.business.requests.CityRequests.UpdateCityRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/cities")
public class CitiesController {
    private final CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("getall")
    public ResponseEntity<DataResult<List<CityListDto>>> getAll() {
        return ResponseEntity.ok(cityService.getAll());
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid CreateCityRequest createCityRequest) {
        var result = cityService.add(createCityRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);

    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCityRequest updateCityRequest) {
        var result = cityService.update(updateCityRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }
}
