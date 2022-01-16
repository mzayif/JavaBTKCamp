package com.btkAkademi.rentACar.business.requests.paymentRequests;

import com.btkAkademi.rentACar.business.requests.bankRequests.BankDto;
import com.btkAkademi.rentACar.business.requests.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest implements IRequest {
	private int rentalId;
	private double totalPrice;
	private BankDto bankDto;
	private boolean isSaveCreditCard;

	public CreatePaymentRequest(int rentalId, double totalPrice) {
		this.rentalId = rentalId;
		this.totalPrice = totalPrice;
	}
}
