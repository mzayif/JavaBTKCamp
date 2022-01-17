package com.btkAkademi.rentACar.business.requests.customerRequests;

import com.btkAkademi.rentACar.business.requests.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest implements IRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String identificationNumber;
    private LocalDate birthDate;
}
