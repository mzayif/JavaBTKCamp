package com.btkAkademi.rentACar.servises.bankServise.concretes;

import com.btkAkademi.rentACar.business.requests.bankRequests.BankDto;
import com.btkAkademi.rentACar.servises.bankServise.abstracts.BankService;
import com.btkAkademi.rentACar.servises.bankServise.outServis.IsBankManager;
import org.springframework.stereotype.Service;

@Service
public class BankAdaptor implements BankService {

    private IsBankManager isBankManager = new IsBankManager();

    @Override
    public boolean CardValid(BankDto bankDto, double price, int bankId) {
        if (bankId == 1)
            return isBankManager.CheckCardValid(bankDto.getCardNumber(), bankDto.getCardName(), bankDto.getCvv(), bankDto.getPrice());

        return false;
    }
}
