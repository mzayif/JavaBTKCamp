package com.btkAkademi.rentACar.business.requests.CarDamageRequests;

import com.btkAkademi.rentACar.business.requests.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamagesRequest implements IRequest {

    private int id;
    private int carId;
    private String description;
}
