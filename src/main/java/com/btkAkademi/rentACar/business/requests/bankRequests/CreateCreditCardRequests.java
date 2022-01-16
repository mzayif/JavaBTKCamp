package com.btkAkademi.rentACar.business.requests.bankRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCreditCardRequests extends BankDto {
    private int customerId;
}
