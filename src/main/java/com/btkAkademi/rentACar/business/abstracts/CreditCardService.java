package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.CreditCardListDto;
import com.btkAkademi.rentACar.business.requests.bankRequests.CreateCreditCardRequests;
import com.btkAkademi.rentACar.business.requests.bankRequests.UpdateCreditCardRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.CreditCard;

import java.util.List;

public interface CreditCardService {
    Result add(CreateCreditCardRequests createCreditCardRequests);
    Result update(UpdateCreditCardRequest updateCreditCardRequest);
    Result delete(int id);

    Result checkIfCreditCardExists(int id);


    DataResult<List<CreditCardListDto>> getAll();
    DataResult<List<CreditCardListDto>> getAllByCustomerId(int customerId);
    DataResult<CreditCard> getById(int id);
}
