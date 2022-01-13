package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.CarDamageListDto;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.CarDamageRequests.CreateCarDamagesRequest;
import com.btkAkademi.rentACar.business.requests.CarDamageRequests.UpdateCarDamagesRequest;
import com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests.CreateCarMaintenanceRequests;
import com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests.UpdateCarMaintenanceRequests;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

import java.util.List;

public interface CarMaintenanceService {
    DataResult<List<CarMaintenanceListDto>> getAll();

    Result add(CreateCarMaintenanceRequests createCarMaintenanceRequests);

    Result update(UpdateCarMaintenanceRequests updateCarMaintenanceRequests);

    Result isCarMaintenance(int carId);
}
