package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.btkAkademi.rentACar.business.abstracts.BaseServices.CheckIfExistService;
import com.btkAkademi.rentACar.core.exeptions.NotFoundException;
import com.btkAkademi.rentACar.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.dtos.BrandListDto;
import com.btkAkademi.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.dataAccess.abstracts.BrandDao;
import com.btkAkademi.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService {
    private final BrandDao brandDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
        this.brandDao = brandDao;
        this.modelMapperService = modelMapperService;
    }

    private Result checkIfBrandExists(String brandName) {
        if (this.brandDao.findByName(brandName).isPresent()) {
            return new ErrorResult(Messages.BRANDNAMEALREADYEXISTS);
        }
        return new SuccessResult();
    }

    private Result checkIfMaximumBrandNumberReached(int limit) {
        if (this.brandDao.count() >= limit) {
            return new ErrorResult(Messages.MAXIMUMBRANDNUMBERREACHED);
        }
        return new SuccessResult();

    }


    @Override
    public Result add(CreateBrandRequest createBrandRequest) {
        var brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);

        var result = BusinessRules.run(checkIfBrandExists(createBrandRequest.getName()),
                checkIfMaximumBrandNumberReached(5));

        if (result != null) {
            return result;
        }
        this.brandDao.save(brand);

        return new SuccessResult(Messages.BRANDADDSUCCESSFUL);
    }

    @Override
    public Result update(UpdateBrandRequest updateBrandRequest) {

        var brand = this.brandDao.findById(updateBrandRequest.getId());

        if (brand.isEmpty()) {
            return new ErrorResult(Messages.BRANDNOTFOUND);
        } else if (updateBrandRequest.getName().equals(brand.get().getName())) {
            return new ErrorResult(Messages.NOUPDATEISSUED);
        }

        var result = BusinessRules.run(checkIfBrandExists(updateBrandRequest.getName()));

        if (result != null) return result;


        brand.get().setName(updateBrandRequest.getName());
        this.brandDao.save(brand.get());

        return new SuccessResult(Messages.BRANDUPDATED);
    }

    @Override
    public Result delete(int id) {
        var brand = this.brandDao.findById(id);
        if (!brand.isPresent()) return new ErrorResult(Messages.NOTFOUND);

        this.brandDao.delete(brand.get());
        return new SuccessResult(Messages.DELETED);
    }


    @Override
    public Result checkIfExists(int id) {
        this.brandDao.findById(id).orElseThrow(() -> new NotFoundException(Messages.NOTFOUND));
        return new SuccessResult();
    }


    @Override
    public DataResult<List<BrandListDto>> getAll() {
        var brandList = this.brandDao.findAll();
        List<BrandListDto> response = brandList.stream().map(brand -> modelMapperService.forDto().map(brand, BrandListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<BrandListDto>>(response);
    }

    @Override
    public DataResult<BrandListDto> get(int id) {
        var brand = this.brandDao.findById(id);
        if (brand.isEmpty()) return new ErrorDataResult<BrandListDto>(Messages.NOTFOUND);
        var brandDto = this.modelMapperService.forRequest().map(brand.get(), BrandListDto.class);
        return brand.isPresent() ? new SuccessDataResult<BrandListDto>(brandDto) : new ErrorDataResult<BrandListDto>();
    }

    @Override
    public DataResult<Brand> getById(int id) {
        var brand = this.brandDao.findById(id);
        return brand.isPresent() ? new SuccessDataResult<Brand>(brand.get()) : new ErrorDataResult<Brand>();
    }

}

