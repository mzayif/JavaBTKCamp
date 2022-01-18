package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.CreditCardService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.RentalExtraServiceService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.bankRequests.BankDto;
import com.btkAkademi.rentACar.business.requests.bankRequests.CreateCreditCardRequests;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.PaymentDao;
import com.btkAkademi.rentACar.entities.concretes.Payment;
import com.btkAkademi.rentACar.servises.bankServise.abstracts.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentManager implements PaymentService {
    private final PaymentDao paymentDao;
    private final RentalService rentalService;
    private final RentalExtraServiceService rentalExtraService;
    private final BankService bankService;
    private final CreditCardService creditCardService;
    private final ModelMapperService modelMapperService;
    private int customerId;

    @Autowired
    public PaymentManager(PaymentDao paymentDao, RentalService rentalService, RentalExtraServiceService rentalExtraService, BankService bankService, CreditCardService creditCardService, ModelMapperService modelMapperService) {
        this.paymentDao = paymentDao;
        this.rentalService = rentalService;
        this.rentalExtraService = rentalExtraService;
        this.bankService = bankService;
        this.creditCardService = creditCardService;
        this.modelMapperService = modelMapperService;
    }

    private Result checkIfRentalPaid(int rentalId) {
        var payments = this.paymentDao.findAllByRentalId(rentalId);
        if (payments.isEmpty() || payments.get().size() == 0)
            return new SuccessResult();

        return new ErrorResult(Messages.ALREADYPAYED);
    }


    public Result add(CreatePaymentRequest createPaymentRequest) {

        var rentalDataResult = this.rentalService.getByRentalId(createPaymentRequest.getRentalId());
        if (rentalDataResult.isSuccess()) return rentalDataResult;

        var rental = rentalDataResult.getData();

        var result = BusinessRules.run(
                checkIfRentalPaid(createPaymentRequest.getRentalId())
        );

        if (result != null) {
            return result;
        }

        var payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
        if (rentalDataResult.getData().getInvoice() != null )
            payment.setInvoice(rentalDataResult.getData().getInvoice());

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
    public Result delete(int id) {
        var payment = this.paymentDao.findById(id);
        if (!payment.isPresent()) return new SuccessResult(Messages.NOTFOUND);

        this.paymentDao.delete(payment.get());
        return new SuccessResult(Messages.DELETED);
    }


    @Override
    public Result payRental(int rentalId) {
        double totalPrice = this.rentalService.getRentalTotalPrice(rentalId);

        if (!this.bankService.CardValid(new BankDto("Test", "32132132132", "06/25", "601", totalPrice), 0, 1))
            return new ErrorResult(Messages.CARDLIMITISNOTVALID);

        CreatePaymentRequest payment = new CreatePaymentRequest(rentalId, totalPrice);
        return add(payment);
    }

    @Override
    public Result payRentalWithCredCard(CreatePaymentRequest createPaymentRequest) {
        var rental = this.rentalService.getByRentalId(createPaymentRequest.getRentalId());
        if (!rental.isSuccess()) return rental;

        double totalPrice = this.rentalService.getRentalTotalPrice(createPaymentRequest.getRentalId());

        if (createPaymentRequest.isSaveCreditCard()) {
            var createCreditCardRequests = this.modelMapperService.forRequest().map(createPaymentRequest, CreateCreditCardRequests.class);
            createCreditCardRequests.setCustomerId(rental.getData().getCustomer().getId());
            creditCardService.add(createCreditCardRequests);
        }


        if (!this.bankService.CardValid(createPaymentRequest.getBankDto(), 0, 1))
            return new ErrorResult(Messages.CARDLIMITISNOTVALID);

        CreatePaymentRequest payment = new CreatePaymentRequest(createPaymentRequest.getRentalId(), totalPrice);
        return add(payment);
    }


    @Override
    public DataResult<List<PaymentListDto>> getAll() {
        var brandList = this.paymentDao.findAll();
        var response = brandList.stream().map(row -> modelMapperService.forDto().map(row, PaymentListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<PaymentListDto>>(response);
    }

    @Override
    public DataResult<List<PaymentListDto>> getAllByRentalId(int rentalId) {
        var paymentList = this.paymentDao.findAllByRentalId(rentalId);
        var response = paymentList.stream().map(row -> modelMapperService.forDto().map(row, PaymentListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<PaymentListDto>>(response);
    }

    @Override
    public DataResult<Payment> getById(int id) {
        var payment = this.paymentDao.findById(id);
        return payment.isPresent() ? new SuccessDataResult<Payment>(payment.get()) : new ErrorDataResult<Payment>();
    }
}
