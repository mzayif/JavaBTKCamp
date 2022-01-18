package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.InvoiceListDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.dtos.RentalInvoiceDetailDto;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.UpdateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.InvoiceDao;
import com.btkAkademi.rentACar.entities.concretes.Invoice;
import com.btkAkademi.rentACar.entities.concretes.RentalExtraService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceManager implements InvoiceService {

    private final InvoiceDao invoiceDao;
    private final RentalService rentalService;
    private final ModelMapperService modelMapperService;

    public InvoiceManager(InvoiceDao invoiceDao, RentalService rentalService, ModelMapperService modelMapperService) {
        this.invoiceDao = invoiceDao;
        this.rentalService = rentalService;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result checkIfInvoiceForRentalExists(int rentalId) {
        if (this.invoiceDao.findAllByRentalId(rentalId).isPresent()) {
            return new ErrorResult(Messages.ALREADYEXISTS);
        }
        return new SuccessResult();
    }



    @Override
    public Result add(CreateInvoiceRequest createInvoiceRequest) {

        var result = BusinessRules.run(
                checkIfInvoiceForRentalExists(createInvoiceRequest.getRentalId()),
                this.rentalService.getByRentalId(createInvoiceRequest.getRentalId())
        );

        if (result != null) {
            return result;
        }

        var invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
        invoice.setTotalPrice(this.rentalService.getRentalTotalPrice(createInvoiceRequest.getRentalId()));

        this.invoiceDao.save(invoice);

        return new SuccessResult(Messages.CREATED);
    }

    @Override
    public Result update(UpdateInvoiceRequest updateInvoiceRequest) {

        var invoice = this.invoiceDao.findById(updateInvoiceRequest.getId());

        if (invoice.isEmpty()) return new ErrorResult(Messages.NOTFOUND);

        invoice.get().setInvoiceDate(updateInvoiceRequest.getInvoiceDate());
        this.invoiceDao.save(invoice.get());
        return new SuccessResult(Messages.UPDATED);
    }

    @Override
    public Result delete(int id) {
        var invoice = this.invoiceDao.findById(id);
        if (!invoice.isPresent()) return new SuccessResult(Messages.NOTFOUND);

        this.invoiceDao.delete(invoice.get());
        return new SuccessResult(Messages.DELETED);
    }


    @Override
    public Result checkIfInvoiceExists(int id) {
        return this.invoiceDao.findById(id).isPresent() ? new SuccessResult() : new ErrorResult(Messages.CARNOTFOUND);
    }


    @Override
    public DataResult<List<InvoiceListDto>> getAll() {
        var invoiceList = this.invoiceDao.findAll();
        var response = invoiceList.stream().map(row -> modelMapperService.forDto().map(row, InvoiceListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<InvoiceListDto>>(response);
    }

    @Override
    public DataResult<InvoiceListDto> getInvoiceDetail(int rentalId) {
        var rentalDataResult =this.rentalService.getByRentalId(rentalId);
        if (!rentalDataResult.isSuccess()) return new ErrorDataResult<>(rentalDataResult.getMessage());

        var rental = rentalDataResult.getData();
        var invoice = this.invoiceDao.findAllByRentalId(rentalId);
        var detail = new InvoiceListDto().builder()
                .rentalId(rentalId)
                .paymentList(rental.getPayments().stream().map(row -> modelMapperService.forDto().map(row, PaymentListDto.class)).collect(Collectors.toList()))
                .build();

        if( invoice.isPresent()) {
            detail.setInvoiceDate(invoice.get().getInvoiceDate());
            detail.setTotalPrice(invoice.get().getTotalPrice());
        }

        var rentalDetail = new RentalInvoiceDetailDto().builder()
                .detailName("Daily Rental Price")
                .price(rental.getCar().getDailyPrice())
                .day((int)ChronoUnit.DAYS.between(rental.getRentDate(), rental.getReturnDate()));

        List<RentalInvoiceDetailDto> detailDtos = new ArrayList<>();
        detailDtos.add(rentalDetail.build());

        for (RentalExtraService extraService : rental.getRentalExtraServices())
        {
            detailDtos.add(new RentalInvoiceDetailDto().builder()
                    .detailName(extraService.getAdditionalService().getServiceName())
                    .price(extraService.getServicePrice())
                    .day((int)ChronoUnit.DAYS.between(rental.getRentDate(), rental.getReturnDate()))
                    .build());
        }

        detail.setInvoiceDetails(detailDtos);

        return new SuccessDataResult<InvoiceListDto>(detail);
    }


    @Override
    public DataResult<Invoice> getById(int id) {
        var invoice = this.invoiceDao.findById(id);
        return invoice.isPresent() ? new SuccessDataResult<Invoice>(invoice.get()) : new ErrorDataResult<Invoice>();
    }
}
