package com.btkAkademi.rentACar.business.requests.IndividualCustomerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
