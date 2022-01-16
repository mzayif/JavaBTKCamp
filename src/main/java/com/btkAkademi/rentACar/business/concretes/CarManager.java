package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.abstracts.ColorService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.entities.concretes.Brand;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDao;
import com.btkAkademi.rentACar.entities.concretes.Car;


/*
*   Araç Kayıt Gereksinimleri
*       1- Araç kayıt ve güncelleme sırasında Brand in varlığı kontrol edilmeli
*       1- Araç kayıt ve güncelleme sırasında Color in varlığı kontrol edilmeli
* */
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

    @Override
    public Result add(CreateCarRequest carCreateDto) {
        var result = BusinessRules.run(
                this.brandService.checkIfBrandExists(carCreateDto.getBrandId()),
                this.colorService.checkIfColorExists(carCreateDto.getColorId())
        );
        if (result != null) {
            return result;
        }

        var car = this.modelMapperService.forRequest().map(carCreateDto, Car.class);

        this.carDao.save(car);
        return new SuccessResult(Messages.CREATED);
    }

    @Override
    public Result update(UpdateCarRequest updateCarRequest) {
        var result = BusinessRules.run(
                this.checkIfCarExists(updateCarRequest.getId()),
                this.brandService.checkIfBrandExists(updateCarRequest.getBrandId()),
                this.colorService.checkIfColorExists(updateCarRequest.getColorId())
        );
        if (result != null) {
            return result;
        }

		var car =this.carDao.findById(updateCarRequest.getId()).get();
		car.setDailyPrice(updateCarRequest.getDailyPrice());
		car.setDescription(updateCarRequest.getDescription());
		car.setFindexScore(updateCarRequest.getFindexScore());
		car.setKilometer(updateCarRequest.getKilometer());
		car.setModelYear(updateCarRequest.getModelYear());
//		car.setBrand(updateCarRequest.getBrand());
//		car.setColor(updateCarRequest.getColor());
		this.carDao.save(car);
        return new SuccessResult(Messages.UPDATED);
    }

    @Override
    public Result delete(int id) {
        var car = this.carDao.findById(id);
        if (!car.isPresent()) return new SuccessResult(Messages.NOTFOUND);

        this.carDao.delete(car.get());
        return new SuccessResult(Messages.DELETED);
    }




    public Result checkIfCarExists(int id) {
        return this.carDao.findById(id).isPresent() ? new SuccessResult() : new ErrorResult(Messages.CARNOTFOUND);
    }

    public Result checkIfCarRental(int id) {
        var car = this.carDao.findById(id);
        if (car.isEmpty())
            return new ErrorResult(Messages.NOTAVAILABLE);

        return new SuccessResult();
    }




    public DataResult<List<CarListDto>> getAll() {
        var carList = this.carDao.findAll();
        var response = carList.stream().map(row -> modelMapperService.forDto().map(row, CarListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarListDto>>(response, Messages.SUCCEED);
    }

    @Override
    public DataResult<List<CarListDto>> getPageable(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        var carList = this.carDao.findAll(pageable).getContent();
        var response = carList.stream().map(row -> modelMapperService.forDto().map(row, CarListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarListDto>>(response, Messages.SUCCEED);

    }

    @Override
    public DataResult<Car> getById(int id) {
        var car = this.carDao.findById(id);
        return car.isPresent() ? new SuccessDataResult<Car>(car.get()) : new ErrorDataResult<Car>();
    }


}
