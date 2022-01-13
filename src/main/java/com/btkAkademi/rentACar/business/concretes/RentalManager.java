package com.btkAkademi.rentACar.business.concretes;


import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.RentalDao;
import com.btkAkademi.rentACar.entities.concretes.Rental;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalManager implements RentalService {
    private final RentalDao rentalDao;
    private final CustomerService customerService;
    private final CarService carService;
    private final CarMaintenanceService carMaintenanceService;
    private final ModelMapperService modelMapperService;

    public RentalManager(RentalDao rentalDao, CustomerService customerService, CarService carService, CarMaintenanceService carMaintenanceService, ModelMapperService modelMapperService) {
        this.rentalDao = rentalDao;
        this.customerService = customerService;
        this.carService = carService;
        this.carMaintenanceService = carMaintenanceService;
        this.modelMapperService = modelMapperService;
    }

    private Result checkDateRule(CreateRentalRequest createRentalRequest){
        if(!createRentalRequest.getRentDate().isBefore(createRentalRequest.getReturnDate()))
            return new ErrorResult(Messages.RETURNDATECANNOTBESMALL);

        return new SuccessResult();
    }
    private Result checkKilometerRule(CreateRentalRequest createRentalRequest){
        if(createRentalRequest.getRentedKilometer() >= createRentalRequest.getReturnedKilometer())
            return new ErrorResult(Messages.RETURNKILOMETERCANNOTBESMALL);

        return new SuccessResult();
    }

    public DataResult<List<RentalListDto>> getAll() {
        var colorList = this.rentalDao.findAll();
        List<RentalListDto> response = colorList.stream()
                .map( rental -> modelMapperService.forDto()
                        .map(rental, RentalListDto.class) )
                .collect(Collectors.toList() );
        return new SuccessDataResult<List<RentalListDto>>(response);
    }

    public DataResult<List<RentalListDto>> getOnRentCars() {
        var colorList = this.rentalDao.findAll();
        List<RentalListDto> response = colorList.stream()
                .map( rental -> modelMapperService.forDto()
                        .map(rental, RentalListDto.class) )
                .collect(Collectors.toList() );
        return new SuccessDataResult<List<RentalListDto>>(response);
    }

    public Result add(CreateRentalRequest createRentalRequest) {

        var rules = BusinessRules.run(
                checkDateRule(createRentalRequest),
                checkKilometerRule(createRentalRequest),
                this.customerService.checkIfCustomer(createRentalRequest.getCustomer().getId()),
                this.carService.checkIfCarExists(createRentalRequest.getCar().getId()),
                this.carService.checkIfCarRental(createRentalRequest.getCar().getId()),
                this.carMaintenanceService.isCarMaintenance(createRentalRequest.getCar().getId())
        );

        if (rules != null) {
            return rules;
        }


        var rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        this.rentalDao.save(rental);
        return new SuccessResult(Messages.RENTALADDSUCCESSFUL);
    }


    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        var color = this.rentalDao.findById(updateRentalRequest.getId());

//		if (color.isEmpty()) {
//			return new ErrorResult(Messages.COLORNOTFOUND);
//		} else if (updateRentalRequest.getName() == color.get().getName()) {
//			return new ErrorResult(Messages.NOUPDATEISSUED);
//		}
//		var result = BusinessRules.run(checkIfColorNameExists(updateRentalRequest.getName()));

//		if (result != null) {
//			return result;
//			//bir değişiklik silelin bunu
//
//		}
        // TODO update işlemi yaz.
        //color.get().setName(updateRentalRequest.getName());
        this.rentalDao.save(color.get());
        return new SuccessResult(Messages.COLORUPDATED);

    }

}
