package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.dtos.InvoiceListDto;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.UpdateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/invoices")
public class InvoicesController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @GetMapping("getall")
    public ResponseEntity<DataResult<List<InvoiceListDto>>> getAll() {
        return ResponseEntity.ok(invoiceService.getAll());
    }

    @GetMapping("getInvoiceDetail")
    public ResponseEntity<DataResult<InvoiceListDto>> getInvoiceDetail(int rentalId) {
        return ResponseEntity.ok(invoiceService.getInvoiceDetail(rentalId));
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid CreateInvoiceRequest createInvoiceRequest) {
        var result = invoiceService.add(createInvoiceRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);

    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateInvoiceRequest updateInvoiceRequest) {
        var result = invoiceService.update(updateInvoiceRequest);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }
}
