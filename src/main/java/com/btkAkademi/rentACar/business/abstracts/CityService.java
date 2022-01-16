package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.btkAkademi.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.City;

import java.util.List;

public interface CityService {
    Result add(CreateCityRequest createCityRequest);
    Result update(UpdateCityRequest updateCityRequest);
    Result delete(int id);

    Result checkIfCityExists(int id);

    DataResult<List<CityListDto>> getAll();
    DataResult<List<CityListDto>> getPageable(int page, int pageSize);
    DataResult<City> getById(int id);
}

