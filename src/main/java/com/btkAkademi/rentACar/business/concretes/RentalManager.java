package com.btkAkademi.rentACar.business.concretes;


import com.btkAkademi.rentACar.business.abstracts.*;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.dtos.RentingPriceDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.RentalDao;
import com.btkAkademi.rentACar.entities.concretes.CreditCard;
import com.btkAkademi.rentACar.entities.concretes.Rental;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


/*
 *   Araç Kiralama Servisi Gereksinimleri
 *
 *       1- Müşterinin varlığı kontrol edilmeli
 *       2- Aracın varlığı kontrol edilmeli
 *       3- Şehirlerin varlığı kontrol edilmeli
 *       4- Aracın bakımda olmadığı kontrol edilmeli
 *       5- Aracın başka biryerde kirada olmadığı kontrol edilmeli
 *       6- Dönüş tarihi kira başlama tarihinden küçük olmamalı
 *       7- Güncellemede dönüş kilometresi başlangıç kilometresinden küçük olmamalı
 * */
@Service
public class RentalManager implements RentalService {
    private final RentalDao rentalDao;
    private final CustomerService customerService;
    private final CarService carService;
    private final CarMaintenanceService carMaintenanceService;
    private final CityService cityService;
    private final ModelMapperService modelMapperService;

    public RentalManager(RentalDao rentalDao, CustomerService customerService, CarService carService, CarMaintenanceService carMaintenanceService, CityService cityService, ModelMapperService modelMapperService) {
        this.rentalDao = rentalDao;
        this.customerService = customerService;
        this.carService = carService;
        this.carMaintenanceService = carMaintenanceService;
        this.cityService = cityService;
        this.modelMapperService = modelMapperService;
    }

    private Result checkDateRule(LocalDate startDate, LocalDate endDate) {
        if (!startDate.isBefore(endDate) && !startDate.equals(endDate))
            return new ErrorResult(Messages.RETURNDATECANNOTBESMALL);

        return new SuccessResult();
    }

    private Result checkKilometerRule(int firstKilometer, int lastKilometer) {
        if (firstKilometer >= lastKilometer)
            return new ErrorResult(Messages.RETURNKILOMETERCANNOTBESMALL);

        return new SuccessResult();
    }


    @Override
    public Result add(CreateRentalRequest createRentalRequest) {

        var rules = BusinessRules.run(
                checkDateRule(createRentalRequest.getRentDate(), createRentalRequest.getReturnDate()),
                this.customerService.checkIfCustomerExists(createRentalRequest.getCustomerId()),
                this.carService.checkIfCarExists(createRentalRequest.getCarId()),
                this.carService.checkIfCarRental(createRentalRequest.getCarId()),
                this.cityService.checkIfCityExists(createRentalRequest.getReturnCityId()),
                this.carMaintenanceService.isCarMaintenance(createRentalRequest.getCarId())
        );

        if (rules != null) {
            return rules;
        }

        if (createRentalRequest.getReturnCityId() > 0 && !this.cityService.checkIfCityExists(createRentalRequest.getReturnCityId()).isSuccess())
            return new ErrorResult(Messages.CITYNOTFOUND);
        //CreditCard creditCard = CreditCard.builder().
        var rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        this.rentalDao.save(rental);
        return new SuccessResult(Messages.RENTALADDSUCCESSFUL);
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        var rentalO = this.rentalDao.findById(updateRentalRequest.getId());

        if (rentalO.isEmpty()) {
            return new ErrorResult(Messages.COLORNOTFOUND);
        }

        var rental = rentalO.get();
        var rules = BusinessRules.run(
                checkDateRule(rental.getRentDate(), rental.getReturnDate()),
                checkKilometerRule(rental.getRentedKilometer(), rental.getReturnedKilometer())
//                this.customerService.checkIfCustomer(rental.getCustomer().getId()),
//                this.carService.checkIfCarRental(rental.getCar().getId()),
//                this.carMaintenanceService.isCarMaintenance(createRentalRequest.getCarId())
        );

        if (rules != null) {
            return rules;
        }

        rental.setRentDate(updateRentalRequest.getReturnDate());
        rental.setReturnedKilometer(updateRentalRequest.getReturnedKilometer());

        this.rentalDao.save(rental);
        return new SuccessResult(Messages.COLORUPDATED);

    }

    @Override
    public Result delete(int id) {
        var car = this.rentalDao.findById(id);
        if (!car.isPresent()) return new SuccessResult(Messages.NOTFOUND);

        this.rentalDao.delete(car.get());
        return new SuccessResult(Messages.DELETED);
    }





    @Override
    public Result isCarRented(int carId) {
        var rentCars = this.rentalDao.getOnRentCars(LocalDate.now());
        return rentCars.isEmpty() ? new SuccessResult() : new ErrorResult(Messages.CARISRENTAL);
    }

    @Override
    public Result checkIfRentalExists(int id) {
        return this.rentalDao.findById(id).isPresent() ? new SuccessResult() : new ErrorResult(Messages.RENTALNOTFOUND);
    }





    @Override
    public DataResult<List<RentalListDto>> getAll() {
        var colorList = this.rentalDao.findAll();
        List<RentalListDto> response = colorList.stream().map(rental -> modelMapperService.forDto().map(rental, RentalListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<RentalListDto>>(response);
    }

    @Override
    public DataResult<List<RentalListDto>> getPageable(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        var rentalList = this.rentalDao.findAll(pageable).getContent();
        var response = rentalList.stream().map(row -> modelMapperService.forDto().map(row, RentalListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<RentalListDto>>(response, Messages.SUCCEED);
    }

    @Override
    public DataResult<List<RentalListDto>> getOnRentCars() {
        var rentCars = this.rentalDao.getOnRentCars(LocalDate.now()).get();
        List<RentalListDto> response = rentCars.stream().map(rental -> modelMapperService.forDto().map(rental, RentalListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<RentalListDto>>(response);
    }

    @Override
    public DataResult<List<RentalListDto>> getAvailableCarForRent() {
        return null;
    }

    @Override
    public DataResult<RentingPriceDto> getRentalDetail(int rentalId) {
        return null;
    }

    @Override
    public DataResult<Rental> getByCarId(int rentalId) {
        var rental = this.rentalDao.findById(rentalId);
        return rental.isPresent() ? new SuccessDataResult<Rental>(rental.get()) : new ErrorDataResult<Rental>(Messages.RENTALNOTFOUND);
    }

    @Override
    public DataResult<Rental> getRentingPrice(int id) {
        return null;
    }


}
