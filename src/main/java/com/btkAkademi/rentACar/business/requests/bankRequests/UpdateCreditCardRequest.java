package com.btkAkademi.rentACar.business.requests.bankRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCreditCardRequest extends BankDto {
    private int customerId;
    private int id;
}
