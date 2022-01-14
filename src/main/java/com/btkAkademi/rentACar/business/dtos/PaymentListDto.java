package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentListDto implements IDto {
    private int id;
    private int rentalId;
    private double totalPrice;
    private boolean isPaid;
}
