package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.requests.CustomerRequest.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.CustomerRequest.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
    private final IndividualCustomerDao individualCustomerDao;
    private final ModelMapperService modelMapperService;


    @Autowired
    public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService) {
        this.individualCustomerDao = individualCustomerDao;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public DataResult<List<IndividualCustomerDao>> getAll() {
        var individualCustomers = this.individualCustomerDao.findAll();
        List<IndividualCustomerDao> response = individualCustomers.stream().map(IndividualCustomer -> modelMapperService.forDto().map(IndividualCustomer, IndividualCustomerDao.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<IndividualCustomerDao>>(response);
    }

    @Override
    public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        if (this.individualCustomerDao.findByEmail(createIndividualCustomerRequest.getEmail()).isPresent())
            return new ErrorResult(Messages.CUSTOMERALREADYEXISTS);

        var year = Calendar.getInstance().get(Calendar.YEAR);
        if (createIndividualCustomerRequest.getBirthDate().getYear() > year-18)
            return new ErrorResult(Messages.CUSTOMERISMINOR);


        var individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
        this.individualCustomerDao.save(individualCustomer);
        return new SuccessResult(Messages.CUSTOMERSUCCESSFUL);
    }

    @Override
    public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
        return null;
    }
}
