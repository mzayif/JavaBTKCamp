package com.btkAkademi.rentACar.business.requests.RentalExtraServiceRequests;

import com.btkAkademi.rentACar.business.requests.IRequest;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalExtraServiceRequest implements IRequest{
	private int additionalServiceId;
	private int rentalId;
	private double servicePrice;
}
