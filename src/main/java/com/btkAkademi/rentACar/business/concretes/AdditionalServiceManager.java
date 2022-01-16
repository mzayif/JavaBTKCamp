package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

    private final AdditionalServiceDao additionalServiceDao;
    private final ModelMapperService modelMapperService;

    public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {
        this.additionalServiceDao = additionalServiceDao;
        this.modelMapperService = modelMapperService;
    }

    private Result checkIfCityExists(String name) {
        var additionalService = this.additionalServiceDao.findByServiceName(name);
        if (additionalService.isPresent()) {
            return new ErrorResult(Messages.ALREADYEXISTS);
        }
        return new SuccessResult();
    }




    @Override
    public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
        var additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);

        var result = BusinessRules.run(
                checkIfCityExists(createAdditionalServiceRequest.getServiceName())
        );

        if (result != null) {
            return result;
        }

        this.additionalServiceDao.save(additionalService);

        return new SuccessResult(Messages.CREATED);
    }

    @Override
    public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {

        var additionalService = this.additionalServiceDao.findById(updateAdditionalServiceRequest.getId());

        if (additionalService.isEmpty()) {
            return new ErrorResult(Messages.NOTFOUND);
        }

        if (!additionalService.get().getServiceName().equals(updateAdditionalServiceRequest.getServiceName())) {
            var otherService = this.additionalServiceDao.findByServiceName(updateAdditionalServiceRequest.getServiceName());
            if (otherService.isPresent())
                return new ErrorResult(Messages.ALREADYEXISTS);
        }

        additionalService.get().setServiceName(updateAdditionalServiceRequest.getServiceName());
        additionalService.get().setServicePrice(updateAdditionalServiceRequest.getServicePrice());
        this.additionalServiceDao.save(additionalService.get());
        return new SuccessResult(Messages.UPDATED);
    }

    @Override
    public Result delete(int id) {
        var brand = this.additionalServiceDao.findById(id);
        if (!brand.isPresent()) return new SuccessResult(Messages.NOTFOUND);

        this.additionalServiceDao.delete(brand.get());
        return new SuccessResult(Messages.DELETED);
    }


    @Override
    public Result checkIfAdditionalServiceExists(int id) {
        return this.additionalServiceDao.findById(id).isPresent() ? new SuccessResult() : new ErrorResult(Messages.NOTFOUND);
    }

    @Override
    public DataResult<List<AdditionalServiceListDto>> getAll() {
        var brandList = this.additionalServiceDao.findAll();
        var response = brandList.stream().map(row -> modelMapperService.forDto().map(row, AdditionalServiceListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<AdditionalServiceListDto>>(response);
    }

    @Override
    public DataResult<AdditionalService> getById(int id) {
        var additionalService = this.additionalServiceDao.findById(id);
        return additionalService.isPresent() ? new SuccessDataResult<AdditionalService>(additionalService.get()) : new ErrorDataResult<AdditionalService>(Messages.NOTFOUND);
    }
}

