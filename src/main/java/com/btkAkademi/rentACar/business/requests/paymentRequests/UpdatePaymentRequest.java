package com.btkAkademi.rentACar.business.requests.paymentRequests;

import com.btkAkademi.rentACar.business.requests.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest implements IRequest {
	private int id;
	private int rentalId;
	private double price;
}
