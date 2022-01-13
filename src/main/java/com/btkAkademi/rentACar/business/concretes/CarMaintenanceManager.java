package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests.CreateCarMaintenanceRequests;
import com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests.UpdateCarMaintenanceRequests;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarMaintenanceDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;
import org.aspectj.bridge.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {
    private final CarMaintenanceDao carMaintenanceDao;
    private final ModelMapperService modelMapperService;
    private final CarService carService;

    public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService, CarService carService) {
        this.carMaintenanceDao = carMaintenanceDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }


    @Override
    public DataResult<List<CarMaintenanceListDto>> getAll() {
        var colorList = this.carMaintenanceDao.findAll();
        var response = colorList.stream().map(color -> modelMapperService.forDto().map(color, CarMaintenanceListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarMaintenanceListDto>>(response);
    }

    @Override
    public Result add(CreateCarMaintenanceRequests createCarMaintenanceRequests) {
        if (createCarMaintenanceRequests.getCar() == null)
            return new ErrorResult(Messages.CARNOTFOUND);
        var checkCarResult = this.carService.checkIfCarExists(createCarMaintenanceRequests.getCar().getId());
        if (!checkCarResult.isSuccess())
            return new ErrorResult(Messages.CARNOTFOUND);

        this.carService.sendMaintenance(createCarMaintenanceRequests.getCar().getId());

        var carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequests, CarMaintenance.class);
        this.carMaintenanceDao.save(carMaintenance);
        return new SuccessResult(Messages.CREATED);
    }

    @Override
    public Result update(UpdateCarMaintenanceRequests updateCarMaintenanceRequests) {
        var carMaintenance = this.carMaintenanceDao.getById(updateCarMaintenanceRequests.getId());

        if (carMaintenance == null) {
            return new ErrorResult(Messages.NOTFOUND);
        }

        this.carService.returnMaintenance(carMaintenance.getCar().getId());
        carMaintenance.setReturnMaintenanceDate(updateCarMaintenanceRequests.getReturnMaintenanceDate());
        this.carMaintenanceDao.save(carMaintenance);

        return new SuccessResult(Messages.UPDATED);
    }

    @Override
    public Result isCarMaintenance(int carId) {
        var carMaintenance = this.carMaintenanceDao.checkCarMaintenance(carId);
        return carMaintenance.isEmpty() || carMaintenance.get().size() == 0 ? new SuccessResult() : new ErrorResult(Messages.NOTAVAILABLE);
    }
}
