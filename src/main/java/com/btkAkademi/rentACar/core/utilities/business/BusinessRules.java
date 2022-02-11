package com.btkAkademi.rentACar.core.utilities.business;

import com.btkAkademi.rentACar.core.utilities.results.Result;

public class BusinessRules {
	public static Result run(Result... logics) {
		for (Result result : logics) {
			if (!result.isSuccess()) {
				return result;
			}
		}
		return null;
	}

	public static boolean checkIfThrow(boolean... logics) {
		for (boolean result : logics) {
			if (!result) {
				return result;
			}
		}
		return true;
	}
}
