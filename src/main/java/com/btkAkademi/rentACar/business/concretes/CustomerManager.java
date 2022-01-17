package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CustomerDao;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements CustomerService {

    private final CustomerDao customerDao;
    private final IndividualCustomerService individualCustomerService;
    private final CorporateCustomerService corporateCustomerService;

    public CustomerManager(CustomerDao customerDao, IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService) {
        this.customerDao = customerDao;
        this.individualCustomerService = individualCustomerService;
        this.corporateCustomerService = corporateCustomerService;
    }

    @Override
    public Result checkIfCustomerExists(int customerId) {
        var customer = customerDao.findById(customerId).isPresent();
        return customer ? new SuccessResult() : new ErrorResult();
    }

    @Override
    public Result checkIfFindexScore(int customerId, int minFindexScore) {

        var result = individualCustomerService.checkIfCustomerExists(customerId);
        if (result.isSuccess()) {
            return individualCustomerService.checkIfFindexScore(customerId, minFindexScore);
        } else
            return corporateCustomerService.checkIfFindexScore(customerId, minFindexScore);

    }
}
