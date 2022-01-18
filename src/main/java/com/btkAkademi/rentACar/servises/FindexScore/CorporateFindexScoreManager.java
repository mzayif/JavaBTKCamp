package com.btkAkademi.rentACar.servises.FindexScore;

import java.util.Random;

public class CorporateFindexScoreManager {

    public int getCorporateFindexScore(String taxNumber) {
        return (int) (Math.random() * 1900 - 650 + 1) + 650;
    }
}
