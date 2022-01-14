package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests.CreateCarMaintenanceRequests;
import com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests.UpdateCarMaintenanceRequests;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Car;

public interface CarService {
	Result add(CreateCarRequest carCreateDto);
	Result update(UpdateCarRequest updateCarRequest);

	Result sendMaintenance(int id);
	Result returnMaintenance(int id);
	Result checkIfCarExists(int id);
	Result checkIfCarRental(int id);

	DataResult<List<CarListDto>> getAll();
	DataResult<List<CarListDto>> getPageable(int page, int pageSize);
	DataResult<Car> getById(int id);

}
