package com.btkAkademi.rentACar.core.utilities.constants;

import com.btkAkademi.rentACar.entities.concretes.Car;

public class Messages {

	public static final String UPDATED = "Record Updated";
	public static final String CREATED = "Record Added";
	public static final String NOTFOUND = "Record Not Found";
	public static final String ALREADYEXISTS = "Record Already Exists";
	public static final String SUCCEED = "Succeed";
    public static final String DELETED = "Record Deleted";
	public static final String NOUPDATEISSUED = "No information has been updated";
	public static final String CANCELED = "Record Canceled";


	public final static String BRANDADDSUCCESSFUL = "brand.added";
	public final static String INVAILABLEBRANDSIZE = "brand.name.invalid";
	public final static String BRANDNAMEALREADYEXISTS = "brand.name.exists";
	public final static String MAXIMUMBRANDNUMBERREACHED = "brand.name.maximum";
	public static final String BRANDNOTFOUND = "brand.notfound";
	public static final String BRANDUPDATED = "brand.updated";


	public final static String CARADDSUCCESSFUL = "car.added";
	public static final String CARNOTFOUND = "car.notfound";
	public static final String CARUPDATED = "car.updated";


	public final static String COLORADDSUCCESSFUL = "color.added";
	public static final String INVAILABLECOLORSIZE = "color.name.invalid";
	public static final String COLORNOTFOUND = "color.notfound";
	public static final String COLORNAMEEXISTS = "color.name.exists";
	public static final String COLORUPDATED = "color.updated";



    public static final String CUSTOMERSUCCESSFUL = "customer.added";
	public static final String CUSTOMERALREADYEXISTS = "customer.name.exists";
	public static final String CUSTOMERISMINOR = "customer.isMinor";
	public static final String CUSTOMERNOTFOUND = "customer.notfound";


	public static final String RENTALADDSUCCESSFUL = "rental.added";
    public static final String RETURNDATECANNOTBESMALL = "return.date.cannot.be.small";
	public static final String RETURNKILOMETERCANNOTBESMALL =  "return.kilometer.cannot.be.small";
	public static final String CARALREADYMAINTENANCE = "Car already maintenance";
    public static final String NOTAVAILABLE = "Car Not Available";
	public static final String CARISRENTAL = "Car is rental";
    public static final String CITYNOTFOUND = "City not found";
    public static final String RENTALNOTFOUND = "Rental not found";
    public static final String ALREADYPAYED = "Rental fee already paid";
    public static final String CARDLIMITISNOTVALID = "Card Limit is not valid";
	public static final String FINDEXSCORENOTENOUGH = "Findex Score Not Enough";
    public static final String MINAGENOTENOUGTH = "Min age not enough to rent a car";
	public static final String NOT_AVAILABLE_OTHER_CAR = "No other available car found in this segment";

    public static String THIS_CAR_NOT_AVAILABLE_BUT_OTHER_THERE_CAR(int id) {
		return "The selected Car is Not Available for the specified day. But in this segment the " + id+ " car is available";
	}
}
