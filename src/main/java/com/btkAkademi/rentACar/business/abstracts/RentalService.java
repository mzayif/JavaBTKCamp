package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.dtos.RentingPriceDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Rental;

import java.util.List;

public interface RentalService {
    Result add(CreateRentalRequest rentalRequest);
    Result addIndividualCustomer(CreateRentalRequest rentalRequest);
    Result addCorporateCustomer(CreateRentalRequest rentalRequest);
    Result update(UpdateRentalRequest updateRentalRequest);
    Result delete(int id);

    Result checkIfRentalExists(int id);
    Result isCarRented(int carId);
    double getRentalTotalPrice(int rentalId);


    DataResult<List<RentalListDto>> getAll();
    DataResult<List<RentalListDto>> getPageable(int page, int pageSize);
    DataResult<List<RentalListDto>> getOnRentCars();
    DataResult<List<RentalListDto>> getAvailableCarForRent();
    DataResult<RentingPriceDto> getRentalDetail(int rentalId);

    DataResult<Rental> getByRentalId(int id);
    DataResult<Rental> getRentingPrice(int id);

}

