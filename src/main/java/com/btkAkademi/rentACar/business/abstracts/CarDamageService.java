package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.CarDamageListDto;
import com.btkAkademi.rentACar.business.requests.CarDamageRequests.CreateCarDamagesRequest;
import com.btkAkademi.rentACar.business.requests.CarDamageRequests.UpdateCarDamagesRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

import java.util.List;

public interface CarDamageService {
    DataResult<List<CarDamageListDto>> getAll();
    DataResult<List<CarDamageListDto>> getAllByCarId(int carId);

    Result add(CreateCarDamagesRequest createCarMaintenanceRequests);

    Result update(UpdateCarDamagesRequest updateCarMaintenanceRequests);

}
