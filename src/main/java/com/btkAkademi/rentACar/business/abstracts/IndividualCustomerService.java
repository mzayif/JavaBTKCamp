package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.customerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.customerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

import java.util.List;

public interface IndividualCustomerService {
    Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
    Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
    Result delete(int id);

    DataResult<List<IndividualCustomerListDto>> getAll();
    DataResult<List<IndividualCustomerListDto>> getPageable(int page, int pageSize);
    DataResult<IndividualCustomer> getById(int id);

}


