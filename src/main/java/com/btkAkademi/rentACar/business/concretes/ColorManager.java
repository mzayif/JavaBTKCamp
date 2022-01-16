package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.entities.concretes.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.ColorService;
import com.btkAkademi.rentACar.business.dtos.ColorListDto;
import com.btkAkademi.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.btkAkademi.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
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

    private Result checkIfColorNameExists(String name) {

        if (!this.colorDao.findByName(name).isPresent()) {
            return new SuccessResult();
        }
        return new ErrorResult(Messages.COLORNAMEEXISTS);
    }



    @Override
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
        }

        color.get().setName(updateColorRequest.getName());
        this.colorDao.save(color.get());
        return new SuccessResult(Messages.COLORUPDATED);
    }

    @Override
    public Result delete(int id) {
        var color = this.colorDao.findById(id);
        if (!color.isPresent()) return new SuccessResult(Messages.NOTFOUND);

        this.colorDao.delete(color.get());
        return new SuccessResult(Messages.DELETED);
    }




    @Override
    public Result checkIfColorExists(int id) {
        return this.colorDao.findById(id).isPresent() ? new SuccessResult() : new ErrorResult(Messages.CARNOTFOUND);
    }




    @Override
    public DataResult<List<ColorListDto>> getAll() {
        var colorList = this.colorDao.findAll();
        List<ColorListDto> response = colorList.stream()
                .map(color -> modelMapperService.forDto()
                        .map(color, ColorListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ColorListDto>>(response);
    }

    @Override
    public DataResult<Color> getById(int id) {
        var color = this.colorDao.findById(id);
        return color.isPresent() ? new SuccessDataResult<Color>(color.get()) : new ErrorDataResult<Color>();
    }
}

