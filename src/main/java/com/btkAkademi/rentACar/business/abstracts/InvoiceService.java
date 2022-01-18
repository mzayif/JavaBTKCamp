package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceListDto;
import com.btkAkademi.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.btkAkademi.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.UpdateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.City;
import com.btkAkademi.rentACar.entities.concretes.Invoice;

import java.util.List;

public interface InvoiceService {
    Result add(CreateInvoiceRequest createCityRequest);
    Result update(UpdateInvoiceRequest updateCityRequest);
    Result delete(int id);

    Result checkIfInvoiceExists(int id);
    Result checkIfInvoiceForRentalExists(int rentalId);

    DataResult<List<InvoiceListDto>> getAll();
    DataResult<InvoiceListDto> getInvoiceDetail(int rentalId);
    DataResult<Invoice> getById(int id);
}
