package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentingPriceDto implements IDto {

    private int id;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private int rentedKilometer;
    private int returnedKilometer;

    private int PicUpCityId;
    private int ReturnCityId;

    private int customerId;
    private int carId;

    private double totalPrice;
    private List<RentalExtraServiceListDto> extraServiceList;
}
