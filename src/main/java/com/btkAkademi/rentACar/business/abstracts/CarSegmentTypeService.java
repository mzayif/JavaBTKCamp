package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.CarSegmentTypeListDto;
import com.btkAkademi.rentACar.business.requests.carSegmentRequests.CreateCarSegmentTypeRequest;
import com.btkAkademi.rentACar.business.requests.carSegmentRequests.UpdateCarSegmentTypeRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.CarSegmentType;

import java.util.List;

public interface CarSegmentTypeService {
    Result add(CreateCarSegmentTypeRequest createCarSegmentTypeRequest);

    Result update(UpdateCarSegmentTypeRequest updateCarSegmentTypeRequest);

    Result delete(int id);

    Result checkIfCarSegmentTypeExists(int id);

    DataResult<List<CarSegmentTypeListDto>> getAll();

    DataResult<CarSegmentType> getById(int id);

}
