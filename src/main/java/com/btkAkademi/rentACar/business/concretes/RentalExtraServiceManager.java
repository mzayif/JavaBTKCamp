package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.RentalExtraServiceService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.RentalExtraServiceListDto;
import com.btkAkademi.rentACar.business.requests.RentalExtraServiceRequests.CreateRentalExtraServiceRequest;
import com.btkAkademi.rentACar.business.requests.RentalExtraServiceRequests.UpdateRentalExtraServiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.RentalExtraServiceDao;
import com.btkAkademi.rentACar.entities.concretes.RentalExtraService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Ekstra Ürün Kiralama Servisi Gereksinimleri
 *   1- Kayıt yapılırken bağlı olduğu Rental kaydı ve ekstra kiralanabilir ürünün varlığı kontol edilmeli
 *   2- Güncellemede ürünün varlığı kontrol edilmeli
 * */
@Service
public class RentalExtraServiceManager implements RentalExtraServiceService {

    private final RentalExtraServiceDao extraServiceDao;
    private final AdditionalServiceService additionalServiceService;
    private final RentalService rentalService;
    private final ModelMapperService modelMapperService;

    public RentalExtraServiceManager(RentalExtraServiceDao extraServiceDao, AdditionalServiceService additionalServiceService, RentalService rentalService, ModelMapperService modelMapperService) {
        this.extraServiceDao = extraServiceDao;
        this.additionalServiceService = additionalServiceService;
        this.rentalService = rentalService;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result add(CreateRentalExtraServiceRequest createRentalExtraServiceRequest) {

        var result = BusinessRules.run(
                this.additionalServiceService.checkIfAdditionalServiceExists(createRentalExtraServiceRequest.getAdditionalServiceId()),
                this.rentalService.checkIfRentalExists(createRentalExtraServiceRequest.getRentalId())
        );

        if (result != null) {
            return result;
        }

        var rentalExtraService = this.modelMapperService.forRequest().map(createRentalExtraServiceRequest, RentalExtraService.class);

        this.extraServiceDao.save(rentalExtraService);
        return new SuccessResult(Messages.CREATED);
    }

    @Override
    public Result update(UpdateRentalExtraServiceRequest updateRentalExtraRequest) {

        var rentalExtraServiceOpt = this.extraServiceDao.findById(updateRentalExtraRequest.getId());

        if (rentalExtraServiceOpt.isEmpty()) {
            return new ErrorResult(Messages.NOTFOUND);
        }

        var result = BusinessRules.run(
                this.additionalServiceService.checkIfAdditionalServiceExists(updateRentalExtraRequest.getAdditionalServiceId()),
                this.rentalService.checkIfRentalExists(updateRentalExtraRequest.getRentalId())
        );

        if (result != null) {
            return result;
        }

        var rentalExtraService = rentalExtraServiceOpt.get();

        if (rentalExtraService.getRental().getId() != updateRentalExtraRequest.getRentalId()) {
            var rental = this.rentalService.getByCarId(updateRentalExtraRequest.getRentalId()).getData();
            rentalExtraService.setRental(rental);
        }
        if (rentalExtraService.getAdditionalService().getId() != updateRentalExtraRequest.getAdditionalServiceId()) {
            var additionalService = this.additionalServiceService.getById(updateRentalExtraRequest.getRentalId()).getData();
            rentalExtraService.setAdditionalService(additionalService);
        }
        rentalExtraService.setServicePrice(updateRentalExtraRequest.getServicePrice());

        this.extraServiceDao.save(rentalExtraService);
        return new SuccessResult(Messages.UPDATED);
    }

    @Override
    public Result checkIfRentalExtraServiceExists(int id) {
        return this.extraServiceDao.findById(id).isPresent() ? new SuccessResult() : new ErrorResult(Messages.NOTFOUND);
    }


    @Override
    public DataResult<List<RentalExtraServiceListDto>> getAll() {
        var serviceDaoAll = this.extraServiceDao.findAll();
        var response = serviceDaoAll.stream().map(row -> modelMapperService.forDto().map(row, RentalExtraServiceListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<RentalExtraServiceListDto>>(response);
    }

    @Override
    public DataResult<RentalExtraService> getById(int id) {
        var rentalExtraService = this.extraServiceDao.findById(id);
        return rentalExtraService.isPresent() ? new SuccessDataResult<RentalExtraService>(rentalExtraService.get()) : new ErrorDataResult<RentalExtraService>(Messages.NOTFOUND);
    }

    @Override
    public DataResult<List<RentalExtraService>> getByRentalId(int rentalId) {
        var serviceDaoAll = this.extraServiceDao.findAllByRentalId(rentalId);
        return new SuccessDataResult<List<RentalExtraService>>(serviceDaoAll.get());
    }
}
