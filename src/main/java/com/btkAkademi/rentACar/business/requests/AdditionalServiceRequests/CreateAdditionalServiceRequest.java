package com.btkAkademi.rentACar.business.requests.AdditionalServiceRequests;

import com.btkAkademi.rentACar.business.requests.IRequest;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalServiceRequest implements IRequest{
	@NotBlank
	@Size(min=3, max=20, message = Messages.INVAILABLEBRANDSIZE)
	private String serviceName;
	private double servicePrice;
}
