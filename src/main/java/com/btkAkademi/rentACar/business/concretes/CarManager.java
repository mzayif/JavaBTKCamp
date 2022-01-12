package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDao;
import com.btkAkademi.rentACar.entities.concretes.Car;

@Service
public class CarManager implements CarService {

	private final CarDao carDao;
	private final ModelMapperService modelMapperService;

	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	public DataResult<List<CarListDto>> getAll() {
		var carList = this.carDao.findAll();
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}

	@Override
	public Result addCar(CreateCarRequest carCreateDto) {
		var car = this.modelMapperService.forRequest().map(carCreateDto, Car.class);
		this.carDao.save(car);
		return new SuccessResult(Messages.CARADDSUCCESSFUL);
	}

	@Override
	public Result updateCar(UpdateCarRequest updateCarRequest) {

		if (this.carDao.findById(updateCarRequest.getId()).isEmpty()) {
			return new ErrorResult(Messages.CARNOTFOUND);
		}

		var updatedCar = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(updatedCar);
		
		
		// TODO update işlemi yaz.
//		car.setDailyPrice(updateCarRequest.getDailyPrice());
//		car.setDescription(updateCarRequest.getDescription());
//		car.setFindexScore(updateCarRequest.getFindexScore());
//		car.setKilometer(updateCarRequest.getKilometer());
//		car.setModelYear(updateCarRequest.getModelYear());
//		car.setBrand(updateCarRequest.getBrand());
//		car.setColor(updateCarRequest.getColor());
//		this.carDao.save(car);
		return new SuccessResult(Messages.CARUPDATED);

	}

}
