package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceListDto implements IDto {

    private int id;
    private int rentalId;
    private LocalDate invoiceDate;
    private double totalPrice;
    private List<PaymentListDto> paymentList;
    private List<RentalInvoiceDetailDto> invoiceDetails;

}
