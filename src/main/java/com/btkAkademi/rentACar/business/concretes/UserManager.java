package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.UserService;
import com.btkAkademi.rentACar.business.dtos.UserListDto;
import com.btkAkademi.rentACar.business.requests.userRequests.CreateUserRequest;
import com.btkAkademi.rentACar.business.requests.userRequests.UpdateUserRequest;
import com.btkAkademi.rentACar.core.exeptions.AlreadyExists;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.*;
import com.btkAkademi.rentACar.dataAccess.abstracts.UserDao;
import com.btkAkademi.rentACar.entities.concretes.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;
    private final ModelMapperService modelMapperService;

    public UserManager(UserDao userDao, ModelMapperService modelMapperService) {
        this.userDao = userDao;
        this.modelMapperService = modelMapperService;
    }

    private boolean checkIfUserEmailExists(String email) {
        if (userDao.existsUserByEmail(email))
            throw new AlreadyExists(Messages.ALREADYEXISTS);
        return true;
    }


    /**
     * Base Add metodu. Belirtilen T nesnesi için
     *
     * @param createRequest
     * @return İşlem başarılı tamamlanmış ise @{@link SuccessResult}
     * @throws AlreadyExists Aynı isimden başka bir kayıt var ise
     */
    @Override
    public Result add(CreateUserRequest createRequest) {

        BusinessRules.checkIfThrow(checkIfUserEmailExists(createRequest.getEmail()));

        var user = this.modelMapperService.forRequest().map(createRequest, User.class);
        this.userDao.save(user);
        return new SuccessResult(Messages.CREATED);
    }


    @Override
    public DataResult<List<UserListDto>> getAll() {
        var users = userDao.findAll();
        List<UserListDto> response = users.stream().map(color -> modelMapperService.forDto().map(color, UserListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<UserListDto>>(response);
    }

    @Override
    public DataResult<UserListDto> get(int id) {
        var user = getUserById(id);
        var userListDto = this.modelMapperService.forRequest().map(user, UserListDto.class);
        return new SuccessDataResult<UserListDto>(userListDto);
    }

    @Override
    public DataResult<User> getById(int id) {
        var user = getUserById(id);
        return new SuccessDataResult<User>(user);
    }

    @Override
    public Result checkIfExists(int id) {
        return userDao.existsById(id) ? new SuccessResult() : new ErrorResult();
    }

    @Override
    public Result delete(int id) {
        var user = getUserById(id);

        user.setActive(false);
        userDao.save(user);

        return new SuccessResult(Messages.CANCELED);
    }

    @Override
    public Result update(UpdateUserRequest updateRequest) {
        var user = getUserById(updateRequest.getId());

        user.setEmail(updateRequest.getEmail());

        userDao.save(user);
        return new SuccessResult(Messages.UPDATED);
    }

    @Override
    public User getUserById(int id) {
        return userDao.findById(id).orElseThrow(() -> new UsernameNotFoundException(Messages.NOTFOUND));
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(Messages.NOTFOUND));
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByEmail(username);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
