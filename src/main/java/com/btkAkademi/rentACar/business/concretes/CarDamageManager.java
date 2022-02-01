package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.CarDamageService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.dtos.CarDamageListDto;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.CreateCarDamagesRequest;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.UpdateCarDamagesRequest;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDamageDao;
import com.btkAkademi.rentACar.entities.concretes.Brand;
import com.btkAkademi.rentACar.entities.concretes.CarDamage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarDamageManager implements CarDamageService {
    private final CarDamageDao carDamageDao;
    private final ModelMapperService modelMapperService;
    private final CarService carService;

    public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService, CarService carService) {
        this.carDamageDao = carDamageDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }


    @Override
    public Result add(CreateCarDamagesRequest createCarDamagesRequest) {

        var checkCarResult = this.carService.checkIfCarExists(createCarDamagesRequest.getCarId());
        if (!checkCarResult.isSuccess())
            return new ErrorResult(Messages.CARNOTFOUND);


        var carDamage = this.modelMapperService.forRequest().map(createCarDamagesRequest, CarDamage.class);
        this.carDamageDao.save(carDamage);
        return new SuccessResult(Messages.CREATED);
    }

    @Override
    public Result update(UpdateCarDamagesRequest updateCarDamagesRequest) {
        var carDamage = this.carDamageDao.getById(updateCarDamagesRequest.getId());

        if (carDamage == null) {
            return new ErrorResult(Messages.NOTFOUND);
        }

        carDamage.setDescription(updateCarDamagesRequest.getDescription());
        this.carDamageDao.save(carDamage);

        return new SuccessResult(Messages.UPDATED);
    }

    @Override
    public Result delete(int id) {
        var brand = this.carDamageDao.findById(id);
        if (!brand.isPresent()) return new SuccessResult(Messages.NOTFOUND);

        this.carDamageDao.delete(brand.get());
        return new SuccessResult(Messages.DELETED);
    }



    @Override
    public DataResult<List<CarDamageListDto>> getAll() {
        var colorList = this.carDamageDao.findAll();
        var response = colorList.stream().map(color -> modelMapperService.forDto().map(color, CarDamageListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarDamageListDto>>(response);
    }

    @Override
    public DataResult<List<CarDamageListDto>> getAllByCarId(int carId) {
        var carMaintenances = this.carDamageDao.findAllByCarId(carId);
        var response = carMaintenances.stream().map(row -> modelMapperService.forDto().map(row, CarDamageListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarDamageListDto>>(response);

    }

    @Override
    public DataResult<CarDamage> getById(int carId) {
        var carDamage = this.carDamageDao.findById(carId);
        return carDamage.isPresent() ? new SuccessDataResult<CarDamage>(carDamage.get()) : new ErrorDataResult<CarDamage>();
    }
}
