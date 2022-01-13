package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerListDto implements IDto {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
