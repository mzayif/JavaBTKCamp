package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.CustomerRequest.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.CustomerRequest.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;

import java.util.List;

public interface IndividualCustomerService {

    DataResult<List<IndividualCustomerListDto>> getAll();

    Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
    Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
}


