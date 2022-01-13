package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.CustomerRequest.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.CustomerRequest.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;

import java.util.List;

public interface CorporateCustomerService {

    DataResult<List<CorporateCustomerListDto>> getAll();

    Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
    Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
}
