package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.requests.IndividualCustomerRequest.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;

import java.util.List;

public interface CorporateCustomerService {

    DataResult<List<CorporateCustomerDao>> getAll();

    Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
}
