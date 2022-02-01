package com.btkAkademi.rentACar.business.abstracts.BaseServices;

import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.entities.concretes.Brand;

import java.util.List;

public interface BaseGetService<T,E> {
    DataResult<List<T>> getAll();
    DataResult<T> get(int id);
    DataResult<E> getById(int id);

}
