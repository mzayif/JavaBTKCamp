package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Payment;

import java.util.List;

public interface PaymentService {
    Result add(CreatePaymentRequest createPaymentRequest);
    Result update(UpdatePaymentRequest updatePaymentRequest);
    Result delete(int id);


    Result payRental(int rentalId);
    Result payRentalWithCredCard(CreatePaymentRequest createPaymentRequest);

    DataResult<List<PaymentListDto>> getAll();
    DataResult<List<PaymentListDto>> getAllByRentalId(int rentalId);
    DataResult<Payment> getById(int id);
}
