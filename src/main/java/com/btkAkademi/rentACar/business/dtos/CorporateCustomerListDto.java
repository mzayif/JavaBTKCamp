package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerListDto implements IDto {
    private int id;
    private String email;
    private String password;
    private String companyName;
    private String taxName;
}
