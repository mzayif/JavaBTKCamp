package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CustomerService {
    Result checkIfCustomerExists(int customerId);
    Result checkIfFindexScore(int customerId, int minFindexScore);

}
