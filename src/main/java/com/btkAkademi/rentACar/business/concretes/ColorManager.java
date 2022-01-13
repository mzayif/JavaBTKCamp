package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests.CreateCarMaintenanceRequests;
import com.btkAkademi.rentACar.business.requests.CarMaintenanseRequests.UpdateCarMaintenanceRequests;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarMaintenanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.ColorService;
import com.btkAkademi.rentACar.business.dtos.ColorListDto;
import com.btkAkademi.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.btkAkademi.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.ColorDao;
import com.btkAkademi.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {
    private final ColorDao colorDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
        this.colorDao = colorDao;
        this.modelMapperService = modelMapperService;
    }


    public DataResult<List<ColorListDto>> getAll() {
        var colorList = this.colorDao.findAll();
        List<ColorListDto> response = colorList.stream()
                .map(color -> modelMapperService.forDto()
                        .map(color, ColorListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ColorListDto>>(response);
    }

    public Result add(CreateColorRequest createColorRequest) {

        var result = BusinessRules.run(checkIfColorNameExists(createColorRequest.getName()));

        if (result != null) {
            return result;
        }

        var color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
        this.colorDao.save(color);
        return new SuccessResult(Messages.COLORADDSUCCESSFUL);
    }


    @Override
    public Result update(UpdateColorRequest updateColorRequest) {
        var color = this.colorDao.findById(updateColorRequest.getId());

        if (color.isEmpty()) {
            return new ErrorResult(Messages.COLORNOTFOUND);
        } else if (updateColorRequest.getName() == color.get().getName()) {
            return new ErrorResult(Messages.NOUPDATEISSUED);
        }
        var result = BusinessRules.run(checkIfColorNameExists(updateColorRequest.getName()));

        if (result != null) {
            return result;
            //bir değişiklik silelin bunu

        }
        // TODO update işlemi yaz.
        color.get().setName(updateColorRequest.getName());
        this.colorDao.save(color.get());
        return new SuccessResult(Messages.COLORUPDATED);

    }

    @Override
    public Result checkIfColorExists(int id) {
        return this.colorDao.findById(id).isPresent() ? new SuccessResult() : new ErrorResult(Messages.CARNOTFOUND);
    }

    private Result checkIfColorNameExists(String name) {

        if (!this.colorDao.findByName(name).isPresent()) {
            return new SuccessResult();
        }
        return new ErrorResult(Messages.COLORNAMEEXISTS);
    }
}

