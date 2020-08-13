package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.User;
import com.vyara.fantasy.data.models.service.ChangeEmailServiceModel;
import com.vyara.fantasy.data.models.service.ChangePasswordServiceModel;
import com.vyara.fantasy.data.models.service.UserRegisterServiceModel;
import com.vyara.fantasy.errors.EntityAlreadyExistsException;
import com.vyara.fantasy.repositories.UserRepository;
import com.vyara.fantasy.services.AuthenticatedUserService;
import com.vyara.fantasy.services.HashingService;
import com.vyara.fantasy.services.RoleService;
import com.vyara.fantasy.services.UserService;
import com.vyara.fantasy.services.validation.EntityValidator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributesException;
import java.util.HashSet;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final RoleService roleService;
    private final AuthenticatedUserService authenticatedUserService;
    private final EntityValidator entityValidator;



    @Override
    public void register(UserRegisterServiceModel model) throws Exception {
        if (!this.entityValidator.arePasswordsValid(model.getPassword(), model.getConfirmPassword())) {
            throw new InvalidAttributesException("Password do not match");
        }
        if (!this.entityValidator.isUserValid(model)){
            throw new EntityAlreadyExistsException("User already exists");
        }
        User user = modelMapper.map(model, User.class);
        user.setPassword(hashingService.hashPass(model.getPassword()));
        setRoles(user);
        userRepository.save(user);

        this.authenticatedUserService.loginAfterRegister(user);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.getByUsername(s);
    }


    private void setRoles(User user) {
        if (this.userRepository.count() == 0) {
            this.roleService.seedRolesInDb();
            user.setAuthorities(this.roleService.getAllRoles());
        } else {
            user.setAuthorities(new HashSet<>());
            user.getAuthorities().add(this.roleService.findByRoleName("USER"));
        }
    }

    @Override
    public void changePassword(ChangePasswordServiceModel model) throws Exception {
        User user = this.authenticatedUserService.getCurrentUser();
        if (!this.entityValidator.arePasswordsValid(model.getNewPassword()
                , model.getConfirmPassword())){
            throw new InvalidAttributesException("Input data do not match");
        }


        user.setPassword(hashingService.hashPass(model.getNewPassword()));
        this.userRepository.save(user);


    }


    @Override
    public void changeEmail(ChangeEmailServiceModel model) throws Exception {
        User user = this.authenticatedUserService.getCurrentUser();
       if (!this.entityValidator.isEmailValid(model.getNewEmail(), model.getConfirmEmail())){
            throw new InvalidAttributesException("Input data do not match");
        }

        user.setEmail(model.getNewEmail());
        this.userRepository.save(user);

    }

    @Override
    public long getUsersCount(){
        return this.userRepository.findAll().size();
    }

}