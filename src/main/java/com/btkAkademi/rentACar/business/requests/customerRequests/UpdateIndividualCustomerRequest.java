package com.btkAkademi.rentACar.business.requests.customerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
