package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.abstracts.BaseServices.*;
import com.btkAkademi.rentACar.business.dtos.UserListDto;
import com.btkAkademi.rentACar.business.requests.userRequests.CreateUserRequest;
import com.btkAkademi.rentACar.business.requests.userRequests.UpdateUserRequest;
import com.btkAkademi.rentACar.core.auth.AppUserService;
import com.btkAkademi.rentACar.entities.concretes.User;

public interface UserService extends AppUserService,
        AddService<CreateUserRequest>,
        BaseGetService<UserListDto, User>, UpdateService<UpdateUserRequest>, DeleteService, CheckIfExistService {

    User getUserById(int id);

    User getUserByEmail(String email);

}
