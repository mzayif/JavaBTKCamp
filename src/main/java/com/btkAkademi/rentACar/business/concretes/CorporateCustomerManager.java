package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.customerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.customerRequests.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Result add(CreateCorporateCustomerRequest corporateCustomerRequest) {
        if (this.corporateCustomerDao.findByCompanyName(corporateCustomerRequest.getCompanyName()).isPresent())
            return new ErrorResult(Messages.CUSTOMERALREADYEXISTS);

        var corporateCustomer = this.modelMapperService.forRequest().map(corporateCustomerRequest, CorporateCustomer.class);
        this.corporateCustomerDao.save(corporateCustomer);
        return new SuccessResult(Messages.CUSTOMERSUCCESSFUL);
    }

    @Override
    public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
        var customer = this.corporateCustomerDao.findById(updateCorporateCustomerRequest.getId());

        if (!customer.isPresent()) return new ErrorResult(Messages.BRANDNOTFOUND);

        customer.get().setTaxName(updateCorporateCustomerRequest.getTaxName());

        this.corporateCustomerDao.save(customer.get());
        return new SuccessResult(Messages.UPDATED);
    }

    @Override
    public Result delete(int id) {
        var customer = this.corporateCustomerDao.findById(id);
        if (!customer.isPresent()) return new SuccessResult(Messages.NOTFOUND);

        this.corporateCustomerDao.delete(customer.get());
        return new SuccessResult(Messages.DELETED);
    }






    @Override
    public DataResult<List<CorporateCustomerListDto>> getAll() {
        var corporateCustomers = this.corporateCustomerDao.findAll();
        var response = corporateCustomers.stream().map(record -> modelMapperService.forDto().map(record, CorporateCustomerListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CorporateCustomerListDto>>(response);
    }

    @Override
    public DataResult<List<CorporateCustomerListDto>> getPageable(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        var carList = this.corporateCustomerDao.findAll(pageable).getContent();
        var response = carList.stream().map(row -> modelMapperService.forDto().map(row, CorporateCustomerListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CorporateCustomerListDto>>(response, Messages.SUCCEED);
    }

    @Override
    public DataResult<CorporateCustomer> getById(int id) {
        var customer = this.corporateCustomerDao.findById(id);
        return customer.isPresent() ? new SuccessDataResult<CorporateCustomer>(customer.get()) : new ErrorDataResult<CorporateCustomer>();
    }
}
