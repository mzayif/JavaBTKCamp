package com.btkAkademi.rentACar.business.abstracts.BaseServices;

import com.btkAkademi.rentACar.business.requests.IRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface AddService<T extends IRequest> {
    /**
     * Base Add metodu. Belirtilen T nesnesi için
     * @param createRequest
     * @return İşlem başarılı tamamlanmış ise @{@link com.btkAkademi.rentACar.core.utilities.results.SuccessResult}
     * @throws com.btkAkademi.rentACar.core.exeptions.AlreadyExists Aynı isimden başka bir kayıt var ise
     */
    Result add(T createRequest);
}

