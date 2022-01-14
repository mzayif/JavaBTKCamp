package com.btkAkademi.rentACar.business.requests.PaymentRequests;

import com.btkAkademi.rentACar.business.requests.IRequest;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest implements IRequest {
	private int rentalId;
	private double totalPrice;
}
