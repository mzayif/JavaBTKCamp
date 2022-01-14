package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.RentalExtraServiceService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.PaymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.PaymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.PaymentDao;
import com.btkAkademi.rentACar.entities.concretes.Payment;
import com.btkAkademi.rentACar.entities.concretes.RentalExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentManager implements PaymentService {
    private final PaymentDao paymentDao;
    private final RentalService rentalService;
    private final RentalExtraServiceService rentalExtraService;
    private final ModelMapperService modelMapperService;

    @Autowired
    public PaymentManager(PaymentDao paymentDao, RentalService rentalService, RentalExtraServiceService rentalExtraService, ModelMapperService modelMapperService) {
        this.paymentDao = paymentDao;
        this.rentalService = rentalService;
        this.rentalExtraService = rentalExtraService;
        this.modelMapperService = modelMapperService;
    }

    private Result checkIfRentalPaid(int rentalId) {
        var payments = this.paymentDao.findAllByRentalId(rentalId);
        if (payments.isEmpty() || payments.get().size() == 0)
            return new SuccessResult();

        return new ErrorResult(Messages.ALREADYPAYED);
    }

    public Result add(CreatePaymentRequest createPaymentRequest) {
        var result = BusinessRules.run(
                this.rentalService.checkIfRentalExists(createPaymentRequest.getRentalId()),
                checkIfRentalPaid(createPaymentRequest.getRentalId())
        );

        if (result != null) {
            return result;
        }

        var payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
        this.paymentDao.save(payment);

        return new SuccessResult(Messages.CREATED);
    }

    @Override
    public Result update(UpdatePaymentRequest updatePaymentRequest) {

        var payment = this.paymentDao.findById(updatePaymentRequest.getId());

        if (payment.isEmpty()) {
            return new ErrorResult(Messages.BRANDNOTFOUND);
        }
        var result = BusinessRules.run(
                this.rentalService.checkIfRentalExists(updatePaymentRequest.getRentalId()),
                checkIfRentalPaid(updatePaymentRequest.getRentalId())
        );

        if (result != null) {
            return result;
        }

        payment.get().setTotalPrice(updatePaymentRequest.getPrice());
        this.paymentDao.save(payment.get());
        return new SuccessResult(Messages.UPDATED);
    }

    @Override
    public Result payRental(int rentalId) {
        var rental = this.rentalService.getByCarId(rentalId);
        if (!rental.isSuccess()) new ErrorResult(Messages.RENTALNOTFOUND);

        Period time = Period.between(rental.getData().getRentDate(), rental.getData().getReturnDate());
        int day = time.getDays();
        double totalPrice = rental.getData().getCar().getDailyPrice() * day;

        for (RentalExtraService rentalExtraService : rental.getData().getRentalExtraServices())
            totalPrice += rentalExtraService.getAdditionalService().getServicePrice() * day;

        CreatePaymentRequest payment = new CreatePaymentRequest(rentalId, totalPrice);
        return add(payment);
    }

    @Override
    public DataResult<List<PaymentListDto>> getAll() {
        var brandList = this.paymentDao.findAll();
        var response = brandList.stream().map(brand -> modelMapperService.forDto().map(brand, PaymentListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<PaymentListDto>>(response);
    }

    @Override
    public DataResult<Payment> getById(int id) {
        var payment = this.paymentDao.findById(id);
        return payment.isPresent() ? new SuccessDataResult<Payment>(payment.get()) : new ErrorDataResult<Payment>();
    }
}
