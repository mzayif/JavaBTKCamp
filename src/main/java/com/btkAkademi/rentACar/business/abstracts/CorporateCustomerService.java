package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.customerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.customerRequests.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

import java.util.List;

public interface CorporateCustomerService {
    Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
    Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
    Result delete(int id);

    DataResult<List<CorporateCustomerListDto>> getAll();
    DataResult<List<CorporateCustomerListDto>> getPageable(int page, int pageSize);
    DataResult<CorporateCustomer> getById(int id);

}
