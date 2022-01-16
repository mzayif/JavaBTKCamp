package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenanseRequests.CreateCarMaintenanceRequests;
import com.btkAkademi.rentACar.business.requests.carMaintenanseRequests.UpdateCarMaintenanceRequests;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;

import java.util.List;

public interface CarMaintenanceService {
    Result add(CreateCarMaintenanceRequests createCarMaintenanceRequests);
    Result update(UpdateCarMaintenanceRequests updateCarMaintenanceRequests);
    Result delete(int id);

    Result isCarMaintenance(int carId);

    DataResult<List<CarMaintenanceListDto>> getAll();
    DataResult<List<CarMaintenanceListDto>> getAllByCarId(int carId);
    DataResult<CarMaintenance> getById(int id);
}
