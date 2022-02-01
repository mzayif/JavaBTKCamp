package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.CreditCardService;
import com.btkAkademi.rentACar.business.requests.bankRequests.CreateCreditCardRequests;
import com.btkAkademi.rentACar.business.requests.bankRequests.UpdateCreditCardRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/creditCards")
@CrossOrigin
public class CreditCardsController {
    private final CreditCardService creditCardService;


    public CreditCardsController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid CreateCreditCardRequests createCreditCardRequests) {
        var result = creditCardService.add(createCreditCardRequests);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCreditCardRequest updateCreditCardRequest) {
        var result = creditCardService.update(updateCreditCardRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(int id) {
        var result = creditCardService.delete(id);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }


    @GetMapping("getall")
    public ResponseEntity<?> getAll() {
        var result = creditCardService.getAll();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @GetMapping("getAllByCustomerId")
    public ResponseEntity<?> getAllByCustomerId(int customerId) {
        var result = creditCardService.getAllByCustomerId(customerId);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

}
