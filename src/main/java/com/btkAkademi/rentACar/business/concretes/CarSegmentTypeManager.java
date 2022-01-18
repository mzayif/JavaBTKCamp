package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.CarSegmentTypeService;
import com.btkAkademi.rentACar.business.dtos.CarSegmentTypeListDto;
import com.btkAkademi.rentACar.business.requests.carSegmentRequests.CreateCarSegmentTypeRequest;
import com.btkAkademi.rentACar.business.requests.carSegmentRequests.UpdateCarSegmentTypeRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarSegmentTypeDao;
import com.btkAkademi.rentACar.entities.concretes.CarSegmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarSegmentTypeManager implements CarSegmentTypeService {
    private final CarSegmentTypeDao carSegmentTypeDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CarSegmentTypeManager(CarSegmentTypeDao carSegmentTypeDao, ModelMapperService modelMapperService) {
        this.carSegmentTypeDao = carSegmentTypeDao;
        this.modelMapperService = modelMapperService;
    }

    private Result checkIfSegmentNameExists(String name) {

        if (!this.carSegmentTypeDao.findBySegmentName(name).isPresent()) {
            return new SuccessResult();
        }
        return new ErrorResult(Messages.COLORNAMEEXISTS);
    }


    @Override
    public Result add(CreateCarSegmentTypeRequest createCarSegmentTypeRequest) {

        var result = BusinessRules.run(checkIfSegmentNameExists(createCarSegmentTypeRequest.getSegmentName()));

        if (result != null) {
            return result;
        }

        var segmentType = this.modelMapperService.forRequest().map(createCarSegmentTypeRequest, CarSegmentType.class);
        this.carSegmentTypeDao.save(segmentType);
        return new SuccessResult(Messages.CREATED);
    }

    @Override
    public Result update(UpdateCarSegmentTypeRequest updateCarSegmentTypeRequest) {
        var carSegmentType = this.carSegmentTypeDao.findById(updateCarSegmentTypeRequest.getId());

        if (carSegmentType.isEmpty()) {
            return new ErrorResult(Messages.NOTFOUND);
        } else if (updateCarSegmentTypeRequest.getSegmentName() == carSegmentType.get().getSegmentName()) {
            return new ErrorResult(Messages.NOUPDATEISSUED);
        }
        var result = BusinessRules.run(checkIfSegmentNameExists(updateCarSegmentTypeRequest.getSegmentName()));

        if (result != null) {
            return result;
        }

        carSegmentType.get().setSegmentName(updateCarSegmentTypeRequest.getSegmentName());
        carSegmentType.get().setCarType(updateCarSegmentTypeRequest.getCarType());

        this.carSegmentTypeDao.save(carSegmentType.get());
        return new SuccessResult(Messages.UPDATED);
    }

    @Override
    public Result delete(int id) {
        var carSegmentType = this.carSegmentTypeDao.findById(id);
        if (!carSegmentType.isPresent()) return new SuccessResult(Messages.NOTFOUND);

        this.carSegmentTypeDao.delete(carSegmentType.get());
        return new SuccessResult(Messages.DELETED);
    }


    @Override
    public Result checkIfCarSegmentTypeExists(int id) {
        return this.carSegmentTypeDao.findById(id).isPresent() ? new SuccessResult() : new ErrorResult(Messages.NOTFOUND);
    }


    @Override
    public DataResult<List<CarSegmentTypeListDto>> getAll() {
        var colorList = this.carSegmentTypeDao.findAll();
        var response = colorList.stream().map(color -> modelMapperService.forDto().map(color, CarSegmentTypeListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarSegmentTypeListDto>>(response);
    }

    @Override
    public DataResult<CarSegmentType> getById(int id) {
        var carSegmentType = this.carSegmentTypeDao.findById(id);
        return carSegmentType.isPresent() ? new SuccessDataResult<CarSegmentType>(carSegmentType.get()) : new ErrorDataResult<CarSegmentType>();
    }
}
