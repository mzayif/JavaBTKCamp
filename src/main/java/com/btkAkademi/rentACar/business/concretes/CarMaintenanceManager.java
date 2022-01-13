package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests.CreateCarMaintenanceRequests;
import com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests.UpdateCarMaintenanceRequests;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarMaintenanceDao;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
 *   Araç Bakıma Gönderme gereksinimleri
 *      1- Bakıma gönderilecek aracın varlığı kontrol eidlmeli
 *      2- Bakıma göndeirlecek aracın kirada olmadığı kontrol edilmeli
 *      3- Bakıma gönderilecek araç bakımda olmamalı
 *      4- Kaydederken Bakımdna dönüş tarihi null/boş kaydedilir.
 *      5- Güncellerken  bakımdan dönüş bilgisi güncellenir.
 *      6- Aracın bakımda olup olmadığı kontrol edilmeli
 * */
@Service
public class CarMaintenanceManager implements CarMaintenanceService {
    private final CarMaintenanceDao carMaintenanceDao;
    private final ModelMapperService modelMapperService;
    private final RentalService rentalService;
    private final CarService carService;

    public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService, @Lazy RentalService rentalService, CarService carService) {
        this.carMaintenanceDao = carMaintenanceDao;
        this.modelMapperService = modelMapperService;
        this.rentalService = rentalService;
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

        var checkCarResult = this.carService.checkIfCarExists(createCarMaintenanceRequests.getCarId());
        if (!checkCarResult.isSuccess())
            return new ErrorResult(Messages.CARNOTFOUND);

        var result = BusinessRules.run(
                this.rentalService.isCarRented(createCarMaintenanceRequests.getCarId()),
                isCarMaintenance(createCarMaintenanceRequests.getCarId())
        );
        if (result != null) {
            return result;
        }


        this.carService.sendMaintenance(createCarMaintenanceRequests.getCarId());

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
        carMaintenance.setSendMaintenanceDate(updateCarMaintenanceRequests.getSendMaintenanceDate());
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
