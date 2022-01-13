package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.requests.CityRequests.CreateCityRequest;
import com.btkAkademi.rentACar.business.requests.CityRequests.UpdateCityRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.CityDao;
import com.btkAkademi.rentACar.entities.concretes.City;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityManager implements CityService {

    private final CityDao cityDao;
    private final ModelMapperService modelMapperService;

    public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
    }

    private Result checkIfCityExists(String cityName) {
        if (this.cityDao.findByName(cityName).isPresent()) {
            return new ErrorResult(Messages.ALREADYEXISTS);
        }
        return new SuccessResult();
    }

    @Override
    public Result add(CreateCityRequest createCityRequest) {
        var city = this.modelMapperService.forRequest().map(createCityRequest, City.class);

        var result = BusinessRules.run(
                checkIfCityExists(createCityRequest.getName())
        );

        if (result != null) {
            return result;
        }
        this.cityDao.save(city);

        return new SuccessResult(Messages.CREATED);
    }

    @Override
    public Result update(UpdateCityRequest updateCityRequest) {

        var city = this.cityDao.findById(updateCityRequest.getId());

        if (city.isEmpty()) {
            return new ErrorResult(Messages.NOTFOUND);
        } else if (updateCityRequest.getName().equals(city.get().getName())) {
            return new ErrorResult(Messages.NOUPDATEISSUED);
        }

        var result = BusinessRules.run(checkIfCityExists(updateCityRequest.getName()));

        if (result != null) {
            return result;
        }

        city.get().setName(updateCityRequest.getName());
        this.cityDao.save(city.get());
        return new SuccessResult(Messages.UPDATED);
    }


    @Override
    public Result checkIfCityExists(int id) {
        return this.cityDao.findById(id).isPresent() ? new SuccessResult() : new ErrorResult(Messages.CARNOTFOUND);
    }

    @Override
    public DataResult<List<CityListDto>> getAll() {
        var brandList = this.cityDao.findAll();
        var response = brandList.stream().map(brand -> modelMapperService.forDto().map(brand, CityListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CityListDto>>(response);
    }

    @Override
    public DataResult<City> getById(int id) {
        var city = this.cityDao.findById(id);
        return city.isPresent() ? new SuccessDataResult<City>(city.get()) : new ErrorDataResult<City>();
    }
}
