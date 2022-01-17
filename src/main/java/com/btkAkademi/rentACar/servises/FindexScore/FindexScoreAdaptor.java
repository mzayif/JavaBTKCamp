package com.btkAkademi.rentACar.servises.FindexScore;

import com.btkAkademi.rentACar.business.requests.bankRequests.BankDto;
import org.springframework.stereotype.Service;

@Service
public class FindexScoreAdaptor implements FindexService {

    private IndividualFindexScoreManager individualFindexScoreManager = new IndividualFindexScoreManager();
    private CorporateFindexScoreManager corporateFindexScoreManager = new CorporateFindexScoreManager();

    @Override
    public int getFindexScore(String key, PersonType type) {

        if (type == PersonType.PERSON)
            return individualFindexScoreManager.getIndividualFindexScore(key);

        if (type == PersonType.COMPANY)
            return corporateFindexScoreManager.getCorporateFindexScore(key);

        return 0;
    }
}
