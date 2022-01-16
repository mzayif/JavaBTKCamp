package com.btkAkademi.rentACar.business.requests.carDamageRequests;

import com.btkAkademi.rentACar.business.requests.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamagesRequest implements IRequest {

    private int carId;
    private String description;
}
