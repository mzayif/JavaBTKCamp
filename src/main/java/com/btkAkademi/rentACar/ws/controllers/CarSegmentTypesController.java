package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.CarSegmentTypeService;
import com.btkAkademi.rentACar.business.dtos.CarSegmentTypeListDto;
import com.btkAkademi.rentACar.business.requests.carSegmentRequests.CreateCarSegmentTypeRequest;
import com.btkAkademi.rentACar.business.requests.carSegmentRequests.UpdateCarSegmentTypeRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/carSegmentTypes")
public class CarSegmentTypesController {
    private final CarSegmentTypeService carSegmentTypeService;

    public CarSegmentTypesController(CarSegmentTypeService carSegmentTypeService) {
        this.carSegmentTypeService = carSegmentTypeService;
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid CreateCarSegmentTypeRequest createCarSegmentTypeRequest) {
        var result = carSegmentTypeService.add(createCarSegmentTypeRequest);

        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCarSegmentTypeRequest updateCarSegmentTypeRequest) {
        var result = carSegmentTypeService.update(updateCarSegmentTypeRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(int id) {
        var result = carSegmentTypeService.delete(id);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }





    @GetMapping("getall")
    public ResponseEntity<DataResult<List<CarSegmentTypeListDto>>> getAll() {
        var result = carSegmentTypeService.getAll();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

}
