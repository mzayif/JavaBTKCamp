package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.dtos.BrandListDto;
import com.btkAkademi.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;


@RestController
@RequestMapping("api/brands")
@CrossOrigin
public class BrandsController {
    private final BrandService brandService;

    @Autowired
    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }


    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid CreateBrandRequest brandCreateDto) {
        var result = brandService.add(brandCreateDto);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
        var result = brandService.update(updateBrandRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(int id) {
        var result = brandService.delete(id);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }


    @GetMapping("getAll")
//    @ApiOperation(
//            value = "Returns a list of all OIDC users",
//            response = OidcUser.class,
//            responseContainer = "List",
//            responseHeaders = @ResponseHeader(name = TOTAL_COUNT_HEADER, response = Long.class, description = "The total number of OIDC users")
//    )
//    @ApiResponses(value = {
//            @ApiResponse(code = 401, message = "Unauthorized")
//    })
//    @PermissionRequired(Permissions.Constants.ACCESS_MANAGEMENT)
    public ResponseEntity<DataResult<List<BrandListDto>>> getAll() {
        var result = brandService.getAll();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

}

