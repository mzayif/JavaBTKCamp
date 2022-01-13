package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.RentalExtraServiceListDto;
import com.btkAkademi.rentACar.business.requests.RentalExtraServiceRequests.CreateRentalExtraServiceRequest;
import com.btkAkademi.rentACar.business.requests.RentalExtraServiceRequests.UpdateRentalExtraServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.RentalExtraService;

import java.util.List;

public interface RentalExtraServiceService {
    Result add(CreateRentalExtraServiceRequest createRentalExtraServiceRequest);

    Result update(UpdateRentalExtraServiceRequest updateRentalExtraRequest);

    Result checkIfRentalExtraServiceExists(int id);


    DataResult<List<RentalExtraServiceListDto>> getAll();

    DataResult<RentalExtraService> getById(int id);
}
