package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.abstracts.BaseServices.*;
import com.btkAkademi.rentACar.business.dtos.BrandListDto;
import com.btkAkademi.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.entities.concretes.Brand;

public interface BrandService extends AddService<CreateBrandRequest>, BaseGetService<BrandListDto, Brand>, UpdateService<UpdateBrandRequest>, DeleteService, CheckIfExistService {


}

