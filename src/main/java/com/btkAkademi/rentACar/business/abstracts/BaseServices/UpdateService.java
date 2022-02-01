package com.btkAkademi.rentACar.business.abstracts.BaseServices;

import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface UpdateService<T> {
    Result update(T updateRequest);

}
