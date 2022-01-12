package com.btkAkademi.rentACar.business.requests.CustomerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {

    private int id;
    private String email;
    private String password;
    private String companyName;
    private String taxName;
}
