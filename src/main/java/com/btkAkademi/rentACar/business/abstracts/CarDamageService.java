package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.CarDamageListDto;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.CreateCarDamagesRequest;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.UpdateCarDamagesRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.CarDamage;

import java.util.List;

public interface CarDamageService {

    Result add(CreateCarDamagesRequest createCarMaintenanceRequests);
    Result update(UpdateCarDamagesRequest updateCarMaintenanceRequests);
    Result delete(int id);

    DataResult<List<CarDamageListDto>> getAll();
    DataResult<List<CarDamageListDto>> getAllByCarId(int carId);
    DataResult<CarDamage> getById(int carId);
}
