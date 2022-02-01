package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.carDtos.CarDetailDto;
import com.btkAkademi.rentACar.business.requests.carRequests.AvailableCar;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Car;

public interface CarService {
	Result add(CreateCarRequest carCreateDto);
	Result update(UpdateCarRequest updateCarRequest);
	Result delete(int id);

	Result checkIfCarExists(int id);
	Result checkIfCarRental(int carId);

	DataResult<List<CarListDto>> getAll();
	DataResult<List<CarListDto>> getPageable(int page, int pageSize);
	DataResult<CarDetailDto> getOne(int id);
	DataResult<Car> getById(int id);

    DataResult<Car> getAvailableSameTypeCar(int carSegmentTypeId, int cityId);

    DataResult<List<CarListDto>> getAvailableCarForRent();
}
