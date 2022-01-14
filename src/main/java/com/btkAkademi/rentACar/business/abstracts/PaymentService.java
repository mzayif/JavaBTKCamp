package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.BrandListDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.PaymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.PaymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Brand;
import com.btkAkademi.rentACar.entities.concretes.Payment;

import java.util.List;

public interface PaymentService {
    Result add(CreatePaymentRequest createPaymentRequest);
    Result update(UpdatePaymentRequest updatePaymentRequest);
    Result payRental(int rentalId);

    DataResult<List<PaymentListDto>> getAll();
    //DataResult<List<PaymentListDto>> getRentalPrice(int rentalId);
    DataResult<Payment> getById(int id);
}
