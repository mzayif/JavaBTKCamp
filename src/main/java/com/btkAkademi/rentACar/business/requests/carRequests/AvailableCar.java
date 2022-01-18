package com.btkAkademi.rentACar.business.requests.carRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


public class AvailableCar {
    private int id;
    private int maintenancesId;
    private LocalDate returnMaintenanceDate;
    private int rentalId;
    private LocalDate returnDate;
    private int carSegmentTypeId;

    public AvailableCar() {
    }

    public AvailableCar(int id) {
        this.id = id;
    }

    public AvailableCar(int id, int carSegmentTypeId) {
        this.id = id;
        this.carSegmentTypeId = carSegmentTypeId;
    }

    public AvailableCar(int id, LocalDate returnMaintenanceDate, LocalDate returnDate, int carSegmentTypeId) {
        this.id = id;
        this.returnMaintenanceDate = returnMaintenanceDate;
        this.returnDate = returnDate;
        this.carSegmentTypeId = carSegmentTypeId;
    }

    public AvailableCar(int id, int maintenancesId, LocalDate returnMaintenanceDate, int rentalId, LocalDate returnDate) {
        this.id = id;
        this.maintenancesId = maintenancesId;
        this.returnMaintenanceDate = returnMaintenanceDate;
        this.rentalId = rentalId;
        this.returnDate = returnDate;
    }

    public AvailableCar(int id, int maintenancesId, LocalDate returnMaintenanceDate, int rentalId, LocalDate returnDate, int carSegmentTypeId) {
        this.id = id;
        this.maintenancesId = maintenancesId;
        this.returnMaintenanceDate = returnMaintenanceDate;
        this.rentalId = rentalId;
        this.returnDate = returnDate;
        this.carSegmentTypeId = carSegmentTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaintenancesId() {
        return maintenancesId;
    }

    public void setMaintenancesId(int maintenancesId) {
        this.maintenancesId = maintenancesId;
    }

    public LocalDate getReturnMaintenanceDate() {
        return returnMaintenanceDate;
    }

    public void setReturnMaintenanceDate(LocalDate returnMaintenanceDate) {
        this.returnMaintenanceDate = returnMaintenanceDate;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getCarSegmentTypeId() {
        return carSegmentTypeId;
    }

    public void setCarSegmentTypeId(int carSegmentTypeId) {
        this.carSegmentTypeId = carSegmentTypeId;
    }
}
