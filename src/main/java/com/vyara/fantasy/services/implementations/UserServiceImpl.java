package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.User;
import com.vyara.fantasy.data.models.ViewModels.CheckUserViewModel;
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
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
        ServletRequestAttributes attributes;

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
    public void changePassword(ChangePasswordServiceModel changePasswordServiceModel) throws Exception {
        User user = this.authenticatedUserService.getCurrentUser();

        if (!changePasswordServiceModel.getNewPassword().equals(changePasswordServiceModel.getConfirmPassword())
                || hashingService.hashPass(changePasswordServiceModel.getCurrentPassword()).equals(user.getPassword())) {
            throw new InvalidAttributesException("Password do not match");
        }

        user.setPassword(hashingService.hashPass(changePasswordServiceModel.getNewPassword()));
        this.userRepository.save(user);


    }

    @Override
    public void changeEmail(ChangeEmailServiceModel changeEmailServiceModel) throws Exception {
        User user = this.authenticatedUserService.getCurrentUser();
        if (!changeEmailServiceModel.getNewEmail().equals(changeEmailServiceModel.getConfirmEmail())
                || hashingService.hashPass(changeEmailServiceModel.getPassword()).equals(user.getPassword())) {
            throw new InvalidAttributesException("Input data do not match");
        }

        user.setEmail(changeEmailServiceModel.getNewEmail());
        this.userRepository.save(user);

    }

    @Override
    public List<CheckUserViewModel> getCheckUsers(){
        List<CheckUserViewModel> models = new ArrayList<>();
        this.userRepository.findAll().forEach(u->{
            CheckUserViewModel model = new CheckUserViewModel();
            model.setUsername(this.hashingService.hashContent(u.getUsername()));
            model.setEmail(this.hashingService.hashContent(u.getEmail()));
            models.add(model);

        });
        return models;
    };

}