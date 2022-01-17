package com.btkAkademi.rentACar.servises.FindexScore;

import java.util.Random;

public class CorporateFindexScoreManager {

    public int getCorporateFindexScore(String taxNumber) {
        Random random = new Random();
        return random.nextInt(650 - 1900) + 650;
    }
}
