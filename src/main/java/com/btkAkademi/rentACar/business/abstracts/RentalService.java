package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

import java.util.List;

public interface RentalService {
    DataResult<List<RentalListDto>> getAll();

    Result add(CreateRentalRequest rentalRequest);
    Result update(UpdateRentalRequest updateRentalRequest);
}

