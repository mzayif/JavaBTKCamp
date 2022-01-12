package com.btkAkademi.rentACar.business.requests.IndividualCustomerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {

    private String email;
    private String password;
    private String companyName;
    private String taxName;
}
