package com.btkAkademi.rentACar.business.requests.rentalExtraServiceRequests;

import com.btkAkademi.rentACar.business.requests.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalExtraServiceRequest implements IRequest{
	private int additionalServiceId;
	private int rentalId;
	private double servicePrice;
}
