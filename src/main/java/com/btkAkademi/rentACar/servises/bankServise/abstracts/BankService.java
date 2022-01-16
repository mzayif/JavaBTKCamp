package com.btkAkademi.rentACar.servises.bankServise.abstracts;

import com.btkAkademi.rentACar.business.requests.bankRequests.BankDto;

public interface BankService {

    boolean CardValid(BankDto bankDto,double price, int bankId);

}
