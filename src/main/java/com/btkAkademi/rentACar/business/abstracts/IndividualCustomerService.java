package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.IndividualCustomerRequest.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.IndividualCustomerRequest.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;

import java.util.List;

public interface IndividualCustomerService {

    DataResult<List<IndividualCustomerDao>> getAll();

    Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
}


