package com.btkAkademi.rentACar.ws.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.btkAkademi.rentACar.core.utilities.results.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.dtos.BrandListDto;
import com.btkAkademi.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;


@RestController
@RequestMapping("api/brands")
@CrossOrigin
@Tag(name = "Brand Controller", description = "Marka Yönetimi")
public class BrandsController {
    private final BrandService brandService;

    @Autowired
    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }


    @PostMapping("add")
    @Operation(summary = "Yeni Marka ekleme metodu", description = "Bu metod yeni bir marka oluşturur. Marka adı kontrol edilir. Eğer daha önce kayıt yapılmamış ise işlem gerçekleştirilir.", responses = {
            @ApiResponse(responseCode = "201", description = "Kayıt Başarılı",content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> add(@RequestBody @Valid CreateBrandRequest brandCreateDto) {
        var result = brandService.add(brandCreateDto);
        return result.isSuccess() ? ResponseEntity.created(URI.create("/brand/")).body(result) : ResponseEntity.badRequest().body(result);
    }

    @PutMapping("update")

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Marka güncelleme", description = "Bu method var olan markanın ismini günceller", responses = {
            @ApiResponse(responseCode = "200", description = "Kayıt Başarılı",content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
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
    //@Operation(summary = "Marka Listesi", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<DataResult<List<BrandListDto>>> getAll() {
        var result = brandService.getAll();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

}

