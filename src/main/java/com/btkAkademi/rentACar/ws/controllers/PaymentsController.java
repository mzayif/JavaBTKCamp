package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/payments")
@CrossOrigin
public class PaymentsController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid CreatePaymentRequest createPaymentRequest) {
        var result = paymentService.add(createPaymentRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PostMapping("payRental")
    public ResponseEntity<?> add(int rentalId) {
        var result = paymentService.payRental(rentalId);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PostMapping("payRentalWithCredCard")
    public ResponseEntity<?> payRentalWithCredCard(@RequestBody @Valid CreatePaymentRequest createPaymentRequest) {
        var result = paymentService.payRentalWithCredCard(createPaymentRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdatePaymentRequest updatePaymentRequest) {
        var result = paymentService.update(updatePaymentRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(int id) {
        var result = paymentService.delete(id);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }


    @GetMapping("getAll")
    public ResponseEntity<?> getAll() {
        var result = paymentService.getAll();
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @GetMapping("getAllByRentalId")
    public ResponseEntity<?> getAllByRentalId(int rentalId) {
        var result = paymentService.getAllByRentalId(rentalId);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

}
