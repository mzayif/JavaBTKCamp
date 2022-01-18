package com.btkAkademi.rentACar.business.requests.carSegmentRequests;

import com.btkAkademi.rentACar.business.enums.CarType;
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
public class UpdateCarSegmentTypeRequest implements IRequest {
	private int id;
	private String segmentName;
	private CarType carType;

}
