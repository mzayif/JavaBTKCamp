package com.btkAkademi.rentACar.servises.FindexScore;

import java.util.Random;

public class IndividualFindexScoreManager {

    public int getIndividualFindexScore(String identificationNumber) {
        Random random = new Random();
        return random.nextInt(650 - 1900) + 650;
    }
}
