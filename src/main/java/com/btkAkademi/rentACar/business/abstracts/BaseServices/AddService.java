package com.btkAkademi.rentACar.business.abstracts.BaseServices;

import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface AddService<T> {
    Result add(T createRequest);
}
