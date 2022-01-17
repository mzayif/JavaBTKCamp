package com.btkAkademi.rentACar.servises.FindexScore;

import com.btkAkademi.rentACar.business.requests.bankRequests.BankDto;

public interface FindexService {

    int getFindexScore(String key, PersonType type);

}
