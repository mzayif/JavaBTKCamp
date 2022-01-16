package com.btkAkademi.rentACar.business.requests.bankRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDto {

    private String cardName;
    private String cardNumber;
    private String lastDate;
    private String cvv;
    private Double price;
}
