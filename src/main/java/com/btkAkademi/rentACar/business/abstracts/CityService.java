package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.dtos.RentalExtraServiceListDto;
import com.btkAkademi.rentACar.business.requests.AdditionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.AdditionalServiceRequests.UpdateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.CityRequests.CreateCityRequest;
import com.btkAkademi.rentACar.business.requests.CityRequests.UpdateCityRequest;
import com.btkAkademi.rentACar.business.requests.RentalExtraServiceRequests.CreateRentalExtraServiceRequest;
import com.btkAkademi.rentACar.business.requests.RentalExtraServiceRequests.UpdateRentalExtraServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.City;

import java.util.List;

public interface CityService {
    Result add(CreateCityRequest createCityRequest);
    Result update(UpdateCityRequest updateCityRequest);
    Result checkIfCityExists(int id);


    DataResult<List<CityListDto>> getAll();
    DataResult<City> getById(int id);
}

