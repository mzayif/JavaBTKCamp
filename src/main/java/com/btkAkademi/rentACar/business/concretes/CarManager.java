package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.ColorService;
import com.btkAkademi.rentACar.business.dtos.carDtos.CarDetailDto;
import com.btkAkademi.rentACar.business.requests.carRequests.AvailableCar;
import com.btkAkademi.rentACar.core.utilities.results.*;
import org.springframework.context.annotation.Lazy;
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

    private final ModelMapperService modelMapperService;
    private final CarDao carDao;
    private final BrandService brandService;
    private final ColorService colorService;
    private final CarMaintenanceService carMaintenanceService;

    public CarManager(CarDao carDao, ModelMapperService modelMapperService, BrandService brandService, ColorService colorService, @Lazy CarMaintenanceService carMaintenanceService) {
        this.carDao = carDao;
        this.modelMapperService = modelMapperService;
        this.brandService = brandService;
        this.colorService = colorService;
        this.carMaintenanceService = carMaintenanceService;
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

        var car = this.carDao.findById(updateCarRequest.getId()).get();
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
        if (!car.isPresent()) return new ErrorResult(Messages.NOTFOUND);

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

        var availableCars = this.carDao.getAvailableCarsByCarId(LocalDate.now(), car.get().getCity().getId(), car.get().getId());
        //var sameOtherCars = availableCars.stream().filter(x -> x.getId() == car.get().getId()).findFirst();
        if (car != null) return new SuccessResult();

        return new ErrorResult(Messages.NOTAVAILABLE);
    }


    public DataResult<List<CarListDto>> getAll() {
        var carList = this.carDao.findAll();
        var response = carList.stream().map(row -> modelMapperService.forDto().map(row, CarListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarListDto>>(response, Messages.SUCCEED);
    }

    @Override
    public DataResult<List<CarListDto>> getPageable(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        var carList = this.carDao.findAll(pageable).getContent();
        var response = carList.stream().map(row -> modelMapperService.forDto().map(row, CarListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarListDto>>(response, Messages.SUCCEED);

    }

    @Override
    public DataResult<CarDetailDto> getOne(int id) {
        var car = this.carDao.findById(id);
        if (!car.isPresent()) return new ErrorDataResult<>(Messages.NOTFOUND);
        var response = this.modelMapperService.forRequest().map(car.get(), CarDetailDto.class);
        return new SuccessDataResult<CarDetailDto>(response, Messages.SUCCEED);
    }

    @Override
    public DataResult<Car> getById(int id) {
        var car = this.carDao.findById(id);
        return car.isPresent() ? new SuccessDataResult<Car>(car.get()) : new ErrorDataResult<Car>();
    }

    @Override
    public DataResult<Car> getAvailableSameTypeCar(int carSegmentTypeId, int cityId) {

        var availableCars = this.carDao.getAvailableCarsByCityId(LocalDate.now(), cityId);
        if (availableCars.size() == 0) new ErrorDataResult<Car>(Messages.NOT_AVAILABLE_OTHER_CAR);

        // istenen araç müsait değilse aynı segemntte başka araba kontrolü yapılacak
        var sameOtherCars = availableCars.stream().filter(x -> x.getCarSegmentType().getId() == carSegmentTypeId).findFirst();
        if (sameOtherCars.isPresent()) return new SuccessDataResult<Car>(sameOtherCars.get());

        return new ErrorDataResult<Car>(Messages.NOT_AVAILABLE_OTHER_CAR);
    }


}
