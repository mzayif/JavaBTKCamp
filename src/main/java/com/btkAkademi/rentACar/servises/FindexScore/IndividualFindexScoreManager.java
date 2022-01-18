package com.btkAkademi.rentACar.servises.FindexScore;

import java.util.Random;

public class IndividualFindexScoreManager {

    public int getIndividualFindexScore(String identificationNumber) {
        return (int) (Math.random() * 1900 - 650 + 1) + 650;
    }
}
