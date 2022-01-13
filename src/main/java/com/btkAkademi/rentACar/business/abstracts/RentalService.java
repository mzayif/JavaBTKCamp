package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Rental;

import java.util.List;

public interface RentalService {
    DataResult<List<RentalListDto>> getAll();
    DataResult<List<RentalListDto>> getOnRentCars();
    DataResult<Rental> getByCarId(int carId);

    Result add(CreateRentalRequest rentalRequest);
    Result update(UpdateRentalRequest updateRentalRequest);
    Result isCarRented(int carId);
    Result checkIfRentalExists(int id);
}

