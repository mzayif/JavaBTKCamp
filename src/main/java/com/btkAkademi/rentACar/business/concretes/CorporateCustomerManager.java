package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.requests.CustomerRequest.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.CustomerRequest.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {
    private final CorporateCustomerDao corporateCustomerDao;
    private final ModelMapperService modelMapperService;

    public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
        this.corporateCustomerDao = corporateCustomerDao;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public DataResult<List<CorporateCustomerDao>> getAll() {
        var corporateCustomers = this.corporateCustomerDao.findAll();
        var response = corporateCustomers.stream().map(record -> modelMapperService.forDto().map(record, CorporateCustomerDao.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CorporateCustomerDao>>(response);
    }

    @Override
    public Result add(CreateCorporateCustomerRequest corporateCustomerRequest) {
        if (this.corporateCustomerDao.findByCompanyName(corporateCustomerRequest.getCompanyName()).isPresent())
            return new ErrorResult(Messages.CUSTOMERALREADYEXISTS);

        var corporateCustomer = this.modelMapperService.forRequest().map(corporateCustomerRequest, CorporateCustomer.class);
        this.corporateCustomerDao.save(corporateCustomer);
        return new SuccessResult(Messages.CUSTOMERSUCCESSFUL);
    }

    @Override
    public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
        return null;
    }
}
