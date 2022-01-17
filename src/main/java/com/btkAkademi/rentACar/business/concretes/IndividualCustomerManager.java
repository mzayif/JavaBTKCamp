package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.customerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.customerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;
import com.btkAkademi.rentACar.servises.FindexScore.FindexService;
import com.btkAkademi.rentACar.servises.FindexScore.PersonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
    private final IndividualCustomerDao individualCustomerDao;
    private final FindexService findexService;
    private final ModelMapperService modelMapperService;


    @Autowired
    public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, FindexService findexService, ModelMapperService modelMapperService) {
        this.individualCustomerDao = individualCustomerDao;
        this.findexService = findexService;
        this.modelMapperService = modelMapperService;
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
        var customer = this.individualCustomerDao.findById(updateIndividualCustomerRequest.getId());

        if (!customer.isPresent()) return new ErrorResult(Messages.BRANDNOTFOUND);

        customer.get().setFirstName(updateIndividualCustomerRequest.getFirstName());
        customer.get().setLastName(updateIndividualCustomerRequest.getLastName());
        customer.get().setBirthDate(updateIndividualCustomerRequest.getBirthDate());

        this.individualCustomerDao.save(customer.get());
        return new SuccessResult(Messages.UPDATED);
    }

    @Override
    public Result delete(int id) {
        var customer = this.individualCustomerDao.findById(id);
        if (!customer.isPresent()) return new SuccessResult(Messages.NOTFOUND);

        this.individualCustomerDao.delete(customer.get());
        return new SuccessResult(Messages.DELETED);
    }

    @Override
    public Result checkIfFindexScore(int customerId, int minFindexScore) {

        var customer = this.individualCustomerDao.findById(customerId);

        if (!customer.isPresent()) return new ErrorResult(Messages.BRANDNOTFOUND);

        var customerScore = this.findexService.getFindexScore(customer.get().getIdentificationNumber(), PersonType.PERSON);

        return customerScore > minFindexScore ? new SuccessResult():new ErrorResult(Messages.FINDEXSCORENOTENOUGH);
    }

    @Override
    public Result checkIfCustomerExists(int customerId) {
        var customer = individualCustomerDao.findById(customerId).isPresent();
        return customer ? new SuccessResult() : new ErrorResult();
    }


    @Override
    public DataResult<List<IndividualCustomerListDto>> getAll() {
        var individualCustomers = this.individualCustomerDao.findAll();
        List<IndividualCustomerListDto> response = individualCustomers.stream().map(IndividualCustomer -> modelMapperService.forDto().map(IndividualCustomer, IndividualCustomerListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<IndividualCustomerListDto>>(response);
    }

    @Override
    public DataResult<List<IndividualCustomerListDto>> getPageable(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        var carList = this.individualCustomerDao.findAll(pageable).getContent();
        var response = carList.stream().map(row -> modelMapperService.forDto().map(row, IndividualCustomerListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<IndividualCustomerListDto>>(response, Messages.SUCCEED);
    }

    @Override
    public DataResult<IndividualCustomer> getById(int id) {
        var customer = this.individualCustomerDao.findById(id);
        return customer.isPresent() ? new SuccessDataResult<IndividualCustomer>(customer.get()) : new ErrorDataResult<IndividualCustomer>();
    }
}
