package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.CreditCardService;
import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.business.dtos.CreditCardListDto;
import com.btkAkademi.rentACar.business.requests.bankRequests.CreateCreditCardRequests;
import com.btkAkademi.rentACar.business.requests.bankRequests.UpdateCreditCardRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.CreditCardDao;
import com.btkAkademi.rentACar.entities.concretes.CreditCard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardManager implements CreditCardService {

    private final CreditCardDao creditCardDao;
    private final CustomerService customerService;
    private final ModelMapperService modelMapperService;

    public CreditCardManager(CreditCardDao creditCardDao, CustomerService customerService, ModelMapperService modelMapperService) {
        this.creditCardDao = creditCardDao;
        this.customerService = customerService;
        this.modelMapperService = modelMapperService;
    }


    private Result checkIfCreditCardExists(String cardNumber, int customerId) {
        if (this.creditCardDao.findByCardNumberAndCustomerId(cardNumber, customerId).isPresent()) {
            return new ErrorResult(Messages.ALREADYEXISTS);
        }
        return new SuccessResult();
    }



    @Override
    public Result add(CreateCreditCardRequests createCreditCardRequests) {

        var result = BusinessRules.run(
                checkIfCreditCardExists(createCreditCardRequests.getCardNumber(), createCreditCardRequests.getCustomerId()),
                this.customerService.checkIfCustomerExists(createCreditCardRequests.getCustomerId())
        );

        if (result != null) return result;

        var creditCard = this.modelMapperService.forRequest().map(createCreditCardRequests, CreditCard.class);

        this.creditCardDao.save(creditCard);
        return new SuccessResult(Messages.CREATED);
    }

    @Override
    public Result update(UpdateCreditCardRequest updateCreditCardRequest) {
        var creditCardOp =this.creditCardDao.findById(updateCreditCardRequest.getId());
        if(!creditCardOp.isPresent()) return new ErrorResult(Messages.CARNOTFOUND);

        var result = BusinessRules.run(
                checkIfCreditCardExists(updateCreditCardRequest.getCardNumber(), updateCreditCardRequest.getCustomerId()),
                this.customerService.checkIfCustomerExists(updateCreditCardRequest.getCustomerId())
        );

        if (result != null) return result;

        var creditCard= creditCardOp.get();
        creditCard.setCardName(creditCard.getCardNumber());
        creditCard.setCvv(creditCard.getCvv());
        creditCard.setLastDate(creditCard.getLastDate());
        creditCard.setCardName(creditCard.getCardName());

        this.creditCardDao.save(creditCard);
        return new SuccessResult(Messages.UPDATED);
    }

    @Override
    public Result delete(int id) {
        var creditCard = this.creditCardDao.findById(id);
        if (!creditCard.isPresent()) return new SuccessResult(Messages.NOTFOUND);

        this.creditCardDao.delete(creditCard.get());
        return new SuccessResult(Messages.DELETED);
    }




    @Override
    public Result checkIfCreditCardExists(int id) {
        return this.creditCardDao.findById(id).isPresent() ? new SuccessResult() : new ErrorResult(Messages.CARNOTFOUND);
    }




    @Override
    public DataResult<List<CreditCardListDto>> getAll() {
        var creditCardList = this.creditCardDao.findAll();
        var response = creditCardList.stream().map(row -> modelMapperService.forDto().map(row, CreditCardListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CreditCardListDto>>(response, Messages.SUCCEED);
    }

    @Override
    public DataResult<List<CreditCardListDto>> getAllByCustomerId(int customerId) {
        var creditCardList = this.creditCardDao.findAllByCustomerId(customerId);
        var response = creditCardList.stream().map(row -> modelMapperService.forDto().map(row, CreditCardListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CreditCardListDto>>(response, Messages.SUCCEED);
    }

    @Override
    public DataResult<CreditCard> getById(int id) {
        var creditCard = this.creditCardDao.findById(id);
        return creditCard.isPresent() ? new SuccessDataResult<CreditCard>(creditCard.get()) : new ErrorDataResult<CreditCard>();
    }

}
