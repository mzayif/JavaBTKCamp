package com.btkAkademi.rentACar.business.requests.userRequests;

import com.btkAkademi.rentACar.business.requests.bankRequests.BankDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private int id;
    private String email;
    private String password;
}
