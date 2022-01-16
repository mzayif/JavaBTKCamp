package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;

import java.util.List;

public interface AdditionalServiceService {
    Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
    Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);
    Result delete(int id);

    Result checkIfAdditionalServiceExists(int id);


    DataResult<List<AdditionalServiceListDto>> getAll();
    DataResult<AdditionalService> getById(int id);
}
