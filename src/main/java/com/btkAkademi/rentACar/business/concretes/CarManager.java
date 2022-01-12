package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.abstracts.ColorService;
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
	private final BrandService brandService;
	private final ColorService colorService;

	public CarManager(CarDao carDao, ModelMapperService modelMapperService, BrandService brandService, ColorService colorService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
		this.brandService = brandService;
		this.colorService = colorService;
	}

	private Result checkIfCarExists(int id) {
		return this.carDao.findById(id).isPresent() ? new SuccessResult() : new ErrorResult(Messages.CARNOTFOUND);
	}

	public DataResult<List<CarListDto>> getAll() {
		var carList = this.carDao.findAll();
		var response = carList.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response,Messages.SUCCEED);
	}

	@Override
	public Result add(CreateCarRequest carCreateDto) {
		var car = this.modelMapperService.forRequest().map(carCreateDto, Car.class);
		this.carDao.save(car);
		return new SuccessResult(Messages.CREATED);
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {

		var result= BusinessRules.run(
				checkIfCarExists(updateCarRequest.getId()),
				this.brandService.checkIfBrandExists(updateCarRequest.getBrand().getId()),
				this.colorService.checkIfColorExists(updateCarRequest.getBrand().getId())
		);

		if(result != null) {
			return result;
		}

		var updatedCar = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(updatedCar);

//		var car =this.carDao.findById(updateCarRequest.getId()).get();
//		car.setDailyPrice(updateCarRequest.getDailyPrice());
//		car.setDescription(updateCarRequest.getDescription());
//		car.setFindexScore(updateCarRequest.getFindexScore());
//		car.setKilometer(updateCarRequest.getKilometer());
//		car.setModelYear(updateCarRequest.getModelYear());
//		car.setBrand(updateCarRequest.getBrand());
//		car.setColor(updateCarRequest.getColor());
//		this.carDao.save(car);
		return new SuccessResult(Messages.UPDATED);
	}

}
