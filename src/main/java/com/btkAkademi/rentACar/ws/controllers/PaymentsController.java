package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.PaymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.PaymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/payments")
public class PaymentsController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("getall")
    public ResponseEntity<DataResult<List<PaymentListDto>>> getAll() {
        return ResponseEntity.ok(paymentService.getAll());
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid CreatePaymentRequest createPaymentRequest) {
        var result = paymentService.add(createPaymentRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);

    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdatePaymentRequest updatePaymentRequest) {
        var result = paymentService.update(updatePaymentRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @PostMapping("payRental")
    public ResponseEntity<?> update(int rentalId) {
        var result = paymentService.payRental(rentalId);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }
}
